package com.atguigu.myforecast.gson;

/**
 * Created by 杨阳洋 on 2017/1/25.
 */

public class Aqi {

    /**
     * city : {"aqi":"207","co":"3","no2":"83","o3":"38","pm10":"156","pm25":"157","qlty":"重度污染","so2":"45"}
     */

    public CityBean city;

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * aqi : 207
         * co : 3
         * no2 : 83
         * o3 : 38
         * pm10 : 156
         * pm25 : 157
         * qlty : 重度污染
         * so2 : 45
         */

        public String aqi;
        private String co;
        private String no2;
        private String o3;
        private String pm10;
        public String pm25;
        private String qlty;
        private String so2;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getCo() {
            return co;
        }

        public void setCo(String co) {
            this.co = co;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getO3() {
            return o3;
        }

        public void setO3(String o3) {
            this.o3 = o3;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getQlty() {
            return qlty;
        }

        public void setQlty(String qlty) {
            this.qlty = qlty;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }
    }
}
