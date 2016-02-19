package com.meilekuan.zhushou_1514.other.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.other.utils.FileUtil;
import com.meilekuan.zhushou_1514.other.utils.JumpManager;
import com.meilekuan.zhushou_1514.other.utils.LogUtil;
import com.meilekuan.zhushou_1514.other.utils.OtherHttpUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouContans;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.logging.MemoryHandler;

/**
 *
 */
public class MainActivity extends Activity {

    private ImageView ivIcon, ivLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);

            if (packageInfo != null) {
                final String versionName = packageInfo.versionName;
                LogUtil.e("mlk", "versionName:" + versionName);
                OtherHttpUtil.requestVersion(versionName, new ZhuShouTask.RequestCallback() {
                    @Override
                    public void success(Object result) {
                        try {
                            JSONObject jsonObject = new JSONObject(result.toString());
                            String state = jsonObject.getString(ZhuShouContans.FLAG_REQUEST_STATE);
                            if (ZhuShouContans.FLAG_REQUEST_SUCCESS.equals(state)) {
                                JSONObject info = jsonObject.getJSONObject("info");
                                String msg = info.getString("msg");

                                msg = Html.fromHtml(msg).toString();
                                String apkUrl = info.getString("src");

                                String ver = info.getString("ver");
                                if (versionName.equals(ver)){
                                    showAnimation();
                                    return;
                                }

                                //如果请求成功了，那么用dialog显示版本信息
                                Dialog dialog = createDialog(msg,apkUrl);
                                dialog.show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void error(String msg) {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                });
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        //如果第一次使用进入该应用，那么跳转到引导页
        if (isFirstUsed()) {
            Intent intent = new Intent(this, GuideActivity.class);
            startActivity(intent);
            finish();
        }
        //否则进到主页面
        else {
            showAnimation();
        }
    }

    /**
     * 创建一个dialog
     *
     * @return
     */
    @NonNull
    private Dialog createDialog(String msg,final String apkUrl) {
        final Dialog dialog = new Dialog(MainActivity.this, R.style.upgrade_dialog_style);
        dialog.setContentView(R.layout.dialog_upgrade);
        TextView tvMsg = (TextView) dialog.findViewById(R.id.upgrade_msg_tv);
        tvMsg.setText(msg);

        Button buttonCancle = (Button) dialog.findViewById(R.id.upgrade_cancel);
        final Button buttonOK= (Button) dialog.findViewById(R.id.upgrade_ok_btn);

        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.upgrade_progress);
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  dialog.dismiss();
                showAnimation();
            }
        });
        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果开始下载，那么先显示进度条
                progressBar.setVisibility(View.VISIBLE);
                //点击更新让它不能再点击了
                buttonOK.setEnabled(false);

                //执行下载apk操作
                OtherHttpUtil.downLoadApk(apkUrl, new ZhuShouTask.RequestCallback() {
                    @Override
                    public void success(Object result) {
                        File apk = (File) result;
                        FileUtil.installApk(MainActivity.this, apk);
                    }

                    @Override
                    public void error(String msg) {
                        Toast.makeText(MainActivity.this, "无法下载apk,是不是地址写错了？", Toast.LENGTH_LONG).show();
                    }
                }, new ZhuShouTask.UpgradeProgress() {
                    @Override
                    public void showprogress(int progress) {

                        progressBar.setProgress(progress);
                    }
                });
            }
        });

        return dialog;
    }

    private void showAnimation() {
        setContentView(R.layout.activity_main);
        ivIcon = (ImageView) findViewById(R.id.main_icon_iv);
        ivLabel = (ImageView) findViewById(R.id.main_label_iv);
        //初始化label和icon的动画
        Animation labelAni = AnimationUtils.loadAnimation(this, R.anim.anim_main_label_in);
        final Animation iconAni = AnimationUtils.loadAnimation(this, R.anim.anim_main_icon_in);

        //分别设置动画监听
        labelAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivIcon.startAnimation(iconAni);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iconAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //在icon动画结束后显示icon
                ivIcon.setVisibility(View.VISIBLE);
                //跳转到主页面
                JumpManager.jumpToHome(MainActivity.this);
                //当前页面不需要了
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivLabel.startAnimation(labelAni);
    }

    private boolean isFirstUsed() {
        SharedPreferences preferences = getSharedPreferences(ZhuShouContans.PERFERECE_FIRST_USED, Context.MODE_PRIVATE);
        return preferences.getBoolean(ZhuShouContans.PERFERECE_FLAG_USED, true);
    }
}
