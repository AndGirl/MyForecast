package com.atguigu.myforecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.atguigu.myforecast.util.CacheUtil;

/**
 * 需求：(接口采用和风天气)
 * 1.可以罗列出全国所有的省，市，县(区)
 * 2.可以查看全国任意城市的天气预报
 * 3.可以自由地切换城市，去查看其它城市的天气
 * 4.提供手动更新以及后台自动更新天气功能
 *
 * 我的API Key
 * 13a5b26db2e74496a113ccf19c9503fa
 *
 * 查看最新的天气预报
 * http://guolin.tech/api/weather?cityid=CN101190401&key=13a5b26db2e74496a113ccf19c9503fa
 * cityid就是待查看城市的weather_id,key部分填入的就是我们申请到的API Key
 *
 * GitHub地址:
 * https://github.com/AndGirl/forecastweather.git
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String weather = CacheUtil.getString(MyApplication.getmContext(), "weather");
        if(!TextUtils.isEmpty(weather)) {
            //让用户不用在跳转到选择城市页面
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
