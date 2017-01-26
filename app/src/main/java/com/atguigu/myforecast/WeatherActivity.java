package com.atguigu.myforecast;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.myforecast.gson.Forecast;
import com.atguigu.myforecast.gson.Weather;
import com.atguigu.myforecast.util.CacheUtil;
import com.atguigu.myforecast.util.Constants;
import com.atguigu.myforecast.util.HttpUtil;
import com.atguigu.myforecast.util.LogUtil;
import com.atguigu.myforecast.util.ParseJsonUtil;
import com.bumptech.glide.Glide;

import org.litepal.LitePalApplication;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private ScrollView weatherLayout;

    private Button navButton;

    private TextView titleCity;

    private TextView titleUpdateTime;

    private TextView degreeText;

    private TextView weatherInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiText;

    private TextView pm25Text;

    private TextView comfortText;

    private TextView carWashText;

    private TextView sportText;

    private ImageView bingPicImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让状态栏融为一体
        if(Build.VERSION.SDK_INT >= 21) {
            View dectorView = getWindow().getDecorView();
            dectorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        //让状态栏融为一体

        //初始化各个控件
        initView();

        //缓存到本地
        String weatherString = CacheUtil.getString(LitePalApplication.getContext(), "weather");
        if(weatherString != null && !TextUtils.isEmpty(weatherString)) {
            //有缓存时直接解析天气数据
            LogUtil.e("111","=============="+weatherString);
            Weather weather = ParseJsonUtil.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        }else{
            //无缓存的时候去服务器查询天气
            String weatherId = getIntent().getStringExtra(Constants.WEATHER_ID);
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(weatherId);
        }

        showImage();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        bingPicImg = (ImageView)findViewById(R.id.bing_pic_img);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        navButton = (Button) findViewById(R.id.back_button);
    }

    /**
     * 展示图片
     */
    private void showImage() {
        String bingPic = CacheUtil.getString(LitePalApplication.getContext(), "bing_pic");
        if(bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        }else{
            loadBingPic();
        }
    }

    /**
     * 联网显示图片
     */
    private void loadBingPic() {
        String requestBingPic = Constants.BASE_IMAGE;
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(WeatherActivity.this, "图片进入黑洞了...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                CacheUtil.saveString(LitePalApplication.getContext(),"bing_pic",bingPic);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }
        });
    }

    /**
     * 根据天气id请求城市天气信息
     * @param weatherId
     */
    public void requestWeather(final String weatherId){
        String weatherUrl = Constants.BASE_URL + weatherId + Constants.BASE_KEY;
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败...", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //请求连接地址
                final String responseText = response.body().string();
                final Weather weather = ParseJsonUtil.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(weather != null && "ok".equals(weather.status)) {
                            CacheUtil.saveString(LitePalApplication.getContext(),"weather",responseText);
                            showWeatherInfo(weather);
                        }else{
                            Toast.makeText(WeatherActivity.this, "亲，地址丢失或不存在...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        loadBingPic();
    }

    /**
     * 处理并展示数据(Weather类)
     * @param weather
    */
    private void showWeatherInfo(Weather weather){
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        //近期天气,每次展示前先移出之前视图
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList){
            //展示近期天气
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temprature.max);
            minText.setText(forecast.temprature.min);
            forecastLayout.addView(view);
        }
        //展示空气质量
        if(weather.aqi != null) {
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comfort = "舒适度：" + weather.suggestion.comfort.info;
        String carWash = "洗车指数：" + weather.suggestion.carWash.info;
        String sport = "运行建议：" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
    }
}
