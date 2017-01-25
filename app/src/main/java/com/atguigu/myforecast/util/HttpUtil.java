package com.atguigu.myforecast.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 杨阳洋 on 2017/1/25.
 * 作用：联网操作公共类
 */

public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
