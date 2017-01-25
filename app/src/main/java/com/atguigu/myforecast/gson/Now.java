package com.atguigu.myforecast.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 杨阳洋 on 2017/1/25.
 */

public class Now {

    /**
     * cond : {"code":"101","txt":"多云"}
     * fl : 3
     * hum : 39
     * pcpn : 0
     * pres : 1030
     * tmp : 3
     * vis : 5
     * wind : {"deg":"122","dir":"东北风","sc":"3-4","spd":"12"}
     */
    @SerializedName("cond")
    public CondBean more;
    private String fl;
    private String hum;
    private String pcpn;
    private String pres;
    @SerializedName("tmp")
    public String temperature;
    private String vis;
    private WindBean wind;

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getPcpn() {
        return pcpn;
    }

    public void setPcpn(String pcpn) {
        this.pcpn = pcpn;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public CondBean getMore() {
        return more;
    }

    public void setMore(CondBean more) {
        this.more = more;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public static class CondBean {
        /**
         * code : 101
         * txt : 多云
         */

        private String code;
        @SerializedName("txt")
        public String info;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    public static class WindBean {
        /**
         * deg : 122
         * dir : 东北风
         * sc : 3-4
         * spd : 12
         */

        private String deg;
        private String dir;
        private String sc;
        private String spd;

        public String getDeg() {
            return deg;
        }

        public void setDeg(String deg) {
            this.deg = deg;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public String getSc() {
            return sc;
        }

        public void setSc(String sc) {
            this.sc = sc;
        }

        public String getSpd() {
            return spd;
        }

        public void setSpd(String spd) {
            this.spd = spd;
        }
    }
}
