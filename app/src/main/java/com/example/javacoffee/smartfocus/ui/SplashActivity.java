package com.example.javacoffee.smartfocus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.javacoffee.smartfocus.MainActivity;
import com.example.javacoffee.smartfocus.R;
import com.example.javacoffee.smartfocus.utils.PreferencesUtil;
import com.example.javacoffee.smartfocus.utils.StaticClass;
import com.example.javacoffee.smartfocus.utils.UtilTools;

public class SplashActivity extends AppCompatActivity{
    private TextView tv_splash;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case StaticClass.HANDLER_SPLASH:
                    if(isFirst()){
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }else {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                    }
                    finish();
            }
        }
    };

    //判断是否第一次启动
    private boolean isFirst() {
        boolean isfirst = PreferencesUtil.getBoolean(this,StaticClass.SHARED_IS_FIRST,true);
        if(isfirst) {
            PreferencesUtil.putBoolean(this, StaticClass.SHARED_IS_FIRST, false);
            return true;
        }else {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView() {
        handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH,2000);
        tv_splash = (TextView) findViewById(R.id.tv_splash);
        UtilTools.setFont(this,tv_splash);
    }
}
