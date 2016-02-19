package com.meilekuan.zhushou_1514.my.ui;

import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.my.util.StringUtil;
import com.meilekuan.zhushou_1514.my.util.Util;
import com.meilekuan.zhushou_1514.other.ui.BaseActivity;
import com.meilekuan.zhushou_1514.other.widget.SharePopWindow;

/**
 * function ：用户登录
 * author：Meilekuan
 * date: 2016/1/16 10:38
 */

public class MyUesrloginActivity extends BaseActivity{

    private TextView tvLogin;

    private EditText edUserName,edUserPassword;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        setTitleText(R.string.user_login);
        showLeftImage();
        setRightText(R.string.user_register);

        tvLogin = (TextView) findViewById(R.id.myuser_v_login);
        edUserName = (EditText) findViewById(R.id.myuesr_edt_username);
        edUserPassword = (EditText) findViewById(R.id.myuesr_edt_userpwd);

    }

    @Override
    protected void initEvents() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uesrLogin();
            }
        });

        //注册
        setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUesrloginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void uesrLogin(){
        String phoneNumber = edUserName.getText().toString().trim();
        String password = edUserPassword.getText().toString().trim();
        //校验手机号
        if (StringUtil.isEmptyOrNull(phoneNumber) || !Util.isTelNumber(phoneNumber)) {
            Toast.makeText(this,"请输入正确的11位手机号",Toast.LENGTH_LONG).show();
            return;
        }
        //校验密码
        if (StringUtil.isEmptyOrNull(password) || password.length() < 6 || password.length() > 22) {
            Toast.makeText(this,"请输入6-22位长度的密码",Toast.LENGTH_LONG).show();
            return;
        }
    }
    @Override
    protected void initData() {

    }
}
