package com.atguigu.myforecast.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Toast;

import com.atguigu.myforecast.gson.Weather;
import com.atguigu.myforecast.util.CacheUtil;
import com.atguigu.myforecast.util.Constants;
import com.atguigu.myforecast.util.HttpUtil;
import com.atguigu.myforecast.util.ParseJsonUtil;

import org.litepal.LitePalApplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWeather();
        updateBingPic();
        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int anHour =4 * 60 * 600 * 1000;//没四小时自动更新一次
        long triggerAtTime = SystemClock.elapsedRealtime() + anHour;
        Intent intent1 = new Intent(this, AutoUpdateService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent1, 0);
        manager.cancel(pendingIntent);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新天气信息
     */
    private void updateBingPic() {
        String weather = CacheUtil.getString(LitePalApplication.getContext(), "weather");
        if(!TextUtils.isEmpty(weather)) {
            //有缓存时直接解析天气数据
            Weather weatherBean = ParseJsonUtil.handleWeatherResponse(weather);
            String weatherId = weatherBean.basic.weatherId;
            String weatherUrl = Constants.BASE_URL + weatherId + Constants.BASE_KEY;
            HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Toast.makeText(AutoUpdateService.this, "服务别人去了...", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    Weather weatherBean = ParseJsonUtil.handleWeatherResponse(string);
                    if(weatherBean != null && "ok".equals(weatherBean.status)) {
                        CacheUtil.saveString(LitePalApplication.getContext(),"weather",string);
                    }
                }
            });
        }
    }

    /**
     * 更新图片
     */
    private void updateWeather() {
        String requestBingPic = Constants.BASE_IMAGE;
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String bingPic = response.body().string();
                CacheUtil.saveString(LitePalApplication.getContext(),"weather",bingPic);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }
}
