package com.example.javacoffee.smartfocus.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesUtil {
    public static final String NAME = "Smartfocus";
    public static void putString(Context context,String key,String value){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public static String getString(Context context,String key,String defValue){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        String value = sp.getString(key,defValue);
        return value;
    }
    public static void putInt(Context context,String key,int value){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key,value);
        editor.commit();
    }
    public static int getInt(Context context,String key,int defValue){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        int value = sp.getInt(key,defValue);
        return value;
    }
    public static void putBoolean(Context context,String key,boolean value){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key,value);
        editor.commit();
    }
    public static boolean getBoolean(Context context,String key,boolean defValue){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key,defValue);
        return value;
    }
    public static void deleteprefs(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }
    public static void deleteAll(Context context){
        SharedPreferences sp = context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
    }
}
