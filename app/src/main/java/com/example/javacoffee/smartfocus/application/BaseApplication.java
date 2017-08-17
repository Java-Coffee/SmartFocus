package com.example.javacoffee.smartfocus.application;

import android.app.Application;

import com.example.javacoffee.smartfocus.utils.StaticClass;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by javacoffee on 2017/8/12.
 */

public class BaseApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), StaticClass.BUGLY_KEY, false);
    }
}
