package com.atguigu.myforecast;

import android.app.Application;
import android.content.Context;

/**
 * Created by 杨阳洋 on 2017/1/25.
 * 作用：
 * 获取全局Context,获得一个应用程序级别的Context
 */

public class MyApplication extends Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getmContext(){
        return mContext;
    }

}
