package com.example.javacoffee.smartfocus.utils;

import android.util.Log;

public class L {
    public static final boolean DEBUG = true;
    public static final String TAG = "Smartfocus";
    //log四个等级 DIWE
    public static void d(String text){
        if(DEBUG){
            Log.d(TAG,text);
        }
    }
    public static void i(String text){
        if(DEBUG){
            Log.i(TAG,text);
        }
    }
    public static void w(String text){
        if(DEBUG){
            Log.w(TAG,text);
        }
    }
    public static void e(String text){
        if(DEBUG){
            Log.e(TAG,text);
        }
    }

}
