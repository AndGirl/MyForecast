package com.atguigu.myforecast.util;

import android.text.TextUtils;
import com.atguigu.myforecast.db.City;
import com.atguigu.myforecast.db.County;
import com.atguigu.myforecast.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 杨阳洋 on 2017/1/25.
 * 作用：解析和处理Gson工具类
 */

public class ParseJsonUtil {
    /**
     * 解析和处理服务器返回的省级数据
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)) {
            try{
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0 ; i < allProvinces.length() ; i ++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();//保存数据到数据库
                    LogUtil.v("TAG","省保存成功");
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param response
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId){
        if(!TextUtils.isEmpty(response)) {
            try{
                JSONArray allCities = new JSONArray(response);
                for (int i = 0 ; i < allCities.length() ;i ++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                    LogUtil.v("TAG","市保存成功");
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                    LogUtil.v("TAG","县保存成功");
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
