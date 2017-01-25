package com.atguigu.myforecast.gson;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 杨阳洋 on 2017/1/25.
 * 作用：引用各个单独实体类
 */

public class Weather {
    public String status;//判断依据
    public Basic basic;
    public Aqi aqi;
    public Now now;
    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<Forecast> forecastList;

}
