package com.example.admin.news.Utils;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by admin on 2016-12-13.
 */

public class NohttpInstance {
    private static RequestQueue instance;
    private static Object o = new Object();
    private NohttpInstance(){

    }
    public static RequestQueue getInstance(){
        if (instance ==null){
            synchronized (o){
                if (instance==null){
                  instance = NoHttp.newRequestQueue();
                }
            }
        }
        return instance;
    }
}


