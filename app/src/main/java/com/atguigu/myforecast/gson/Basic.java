package com.atguigu.myforecast.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 杨阳洋 on 2017/1/25.
 */

public class Basic {

    /**
     * city : 北京
     * cnty : 中国
     * id : CN101010100
     * lat : 39.904000
     * lon : 116.391000
     * update : {"loc":"2017-01-25 14:51","utc":"2017-01-25 06:51"}
     */
    @SerializedName("city")
    public String cityName;
    private String cnty;
    @SerializedName("id")
    public String weatherId;
    private String lat;
    private String lon;
    public UpdateBean update;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
    }

    public static class UpdateBean {
        /**
         * loc : 2017-01-25 14:51
         * utc : 2017-01-25 06:51
         */
        @SerializedName("loc")
        public String updateTime;
        private String utc;

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getUtc() {
            return utc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
        }
    }
}
