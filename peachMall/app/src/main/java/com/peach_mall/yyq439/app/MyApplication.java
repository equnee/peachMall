package com.peach_mall.yyq439.app;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;
//import com.zhy.http.okhttp.OkHttpUtils;
import okhttp3.OkHttpClient;


public class MyApplication extends Application {

    private static Context mConext;

    public static Context getContext() {
        return mConext;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        mConext = this;
        initOkhttpClient();
    }

    private void initOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
    }
}