package com.meilekuan.zhushou_1514.active.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.active.adapter.ActiveListAdapter;
import com.meilekuan.zhushou_1514.active.adapter.TopPicAdapter;
import com.meilekuan.zhushou_1514.active.bean.Active;
import com.meilekuan.zhushou_1514.active.bean.ActiveInfo;
import com.meilekuan.zhushou_1514.active.util.ActiveHttpUtil;
import com.meilekuan.zhushou_1514.gift.bean.Gift;
import com.meilekuan.zhushou_1514.gift.ui.GiftInfoActivity;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * function ：活动界面
 * author：Meilekuan
 * date: 2016/1/12 10:39
 */

public class ActiveFragment extends BaseFragment {
    private ListView listView;
    /** 轮播图容器 */
    private ViewPager mTopPicViewPager;
    /** 轮播图文字描述 */
    private TextView mTopPicDesc;
    /** 轮播图指示器 底部小点容器 */
    private LinearLayout mTopPisPointersLayout;
    /** 上一个选中的位置 */
    private int previousEnabledPosition;
    private LunBoTask mLunBoTask;
    /** 是否手指按下 */
    private boolean isDown;
    private List<Active> list = new ArrayList<>();


    static String str1 = "72G游戏助手新版上线";
    static String str2 = "《攻城掠地》火热上线";
    static int[] resIds = new int[]{R.drawable.active_vp1,R.drawable.active_vp2};
    static String[] carousels = new String[]{str1, str2};

    @Override
    protected int getLayout() {
        return R.layout.fragment_active;
    }

    @Override
    protected void initViews() {
        mTopPicViewPager = (ViewPager) root.findViewById(R.id.active_vp_pic);
        mTopPicDesc = (TextView) root.findViewById(R.id.active_tv_carousel);
        mTopPisPointersLayout = (LinearLayout) root.findViewById(R.id.active_ll_pointers);
        mTopPicViewPager.setOnTouchListener(mOnTouchListener);
        startPicturesLunbo();

        listView = (ListView) root.findViewById(R.id.active_list_lv);

    }

    @Override
    protected void initEvents() {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Active active = list.get(position);
                String gameId = active.getId();
                Intent intent = new Intent(getActivity(),ActiveInfoActivity.class);
                intent.putExtra("id",gameId);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {

        final ActiveListAdapter adapter = new ActiveListAdapter(getActivity(),list);
        listView.setAdapter(adapter);

        ActiveHttpUtil.requestActiveList(new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                try {
                    if (result == null) {
                        return;
                    }
                    JSONObject json = new JSONObject(result.toString());
                    String state = json.getString("state");
                    if ("success".equals(state)) {
                        JSONArray info = json.getJSONArray("info");
                        List<Active> actives = Active.arrayActiveFromData(info.toString());
                        list.addAll(actives);
                        //更新listview的数据
                       adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_LONG).show();
            }
        });
    }
    /**
     * 播放顶部轮播图
     */
    private void startPicturesLunbo() {
        List<Integer> topPicList = new ArrayList<>();
        for (int id : resIds) {
            topPicList.add(id);
        }
        TopPicAdapter pagerAdapterTopPic = new TopPicAdapter(getActivity(), topPicList);
        mTopPicViewPager.setAdapter(pagerAdapterTopPic);
        mTopPicViewPager.removeOnPageChangeListener(mTopPicPageChangeListener);
        mTopPicViewPager.addOnPageChangeListener(mTopPicPageChangeListener);
        // 初始化图片的点
        mTopPisPointersLayout.removeAllViewsInLayout();
        mTopPisPointersLayout.removeAllViews(); // 先清空一下,不然会产生多一倍的个数点
        for (int x = 0; x < topPicList.size(); x++) {
            View v = new View(getActivity());
            v.setBackgroundResource(R.drawable.selector_pointers_active);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            if (x != 0) {
                params.leftMargin = 10;
            }
            v.setLayoutParams(params);
            v.setEnabled(false);
            mTopPisPointersLayout.addView(v);
        }
        if (mTopPisPointersLayout.getChildCount() > 0) {
            previousEnabledPosition = 0;
            mTopPisPointersLayout.getChildAt(previousEnabledPosition).setEnabled(true);
            //轮播
            if (mLunBoTask == null) {
                mLunBoTask = new LunBoTask();
                mLunBoTask.start();
            } else if (!isDown) {
                mLunBoTask.stop();
                mLunBoTask.start();
            }
        }
    }

    private ViewPager.OnPageChangeListener mTopPicPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            mTopPisPointersLayout.getChildAt(previousEnabledPosition).setEnabled(false);// 取消前一个高亮
            mTopPisPointersLayout.getChildAt(position).setEnabled(true);// 显示当前的高亮
            mTopPicDesc.setText(carousels[position]);
            previousEnabledPosition = position;
        }
    };
    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    isDown = true;
                    mLunBoTask.stop();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    isDown = false;
                    mLunBoTask.start();
                    break;
            }
            return false;
        }
    };


    private class LunBoTask extends Handler implements Runnable {
        @Override
        public void run() {
            int item = mTopPicViewPager.getCurrentItem();
            mTopPicViewPager.setCurrentItem((++item) % mTopPicViewPager.getAdapter().getCount(), true);
            postDelayed(mLunBoTask, 3000);
        }

        public void start() {
            stop();// 停止当前的轮播,重新开始
            mLunBoTask.postDelayed(mLunBoTask, 3000);
        }

        public void stop() {
            mLunBoTask.removeCallbacks(mLunBoTask);
        }
    }
}
