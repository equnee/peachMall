package com.peach_mall.yyq439.utils;

import android.content.Context;
import android.content.SharedPreferences;
public class CacheUtils {

    public static String getString(Context context, String key) {
        System.out.println("===="+context+"===");
        SharedPreferences sp = context.getSharedPreferences("peach",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void saveString(Context context, String key,String value) {
        SharedPreferences sp = context.getSharedPreferences("peach",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();
    }
}