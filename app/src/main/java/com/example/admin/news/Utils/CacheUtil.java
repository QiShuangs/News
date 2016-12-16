package com.example.admin.news.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by admin on 2016-12-13.
 */

public class CacheUtil {
    private static String CONFIG = "config";
    public static String IS_FIRST = "is_first";
    public static void putBooleanIntoSp(Context context,String key,boolean b){
        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,b).commit();
    }
    public static boolean getBooleanFromSp(Context context,String key,boolean b){
        SharedPreferences sp = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        return sp.getBoolean(key,b);
    }
}
