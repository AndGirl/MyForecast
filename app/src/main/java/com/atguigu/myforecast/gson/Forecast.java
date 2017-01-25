package com.atguigu.myforecast.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 杨阳洋 on 2017/1/25.
 */

public class Forecast {

    /**
     * astro : {"mr":"05:01","ms":"15:07","sr":"07:28","ss":"17:25"}
     * cond : {"code_d":"100","code_n":"104","txt_d":"晴","txt_n":"阴"}
     * date : 2017-01-25
     * hum : 56
     * pcpn : 0.0
     * pop : 0
     * pres : 1032
     * tmp : {"max":"4","min":"-4"}
     * uv : 2
     * vis : 10
     * wind : {"deg":"158","dir":"南风","sc":"微风","spd":"5"}
     */

    private AstroBean astro;
    @SerializedName("cond")
    public CondBean more;
    public String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    @SerializedName("tmp")
    public TmpBean temprature;
    private String uv;
    private String vis;
    private WindBean wind;

    public AstroBean getAstro() {
        return astro;
    }

    public void setAstro(AstroBean astro) {
        this.astro = astro;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres;
    }

    public CondBean getMore() {
        return more;
    }

    public void setMore(CondBean more) {
        this.more = more;
    }

    public TmpBean getTemprature() {
        return temprature;
    }

    public void setTemprature(TmpBean temprature) {
        this.temprature = temprature;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
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

    public static class AstroBean {
        /**
         * mr : 05:01
         * ms : 15:07
         * sr : 07:28
         * ss : 17:25
         */

        private String mr;
        private String ms;
        private String sr;
        private String ss;

        public String getMr() {
            return mr;
        }

        public void setMr(String mr) {
            this.mr = mr;
        }

        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
        }

        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
        }

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
    }

    public static class CondBean {
        /**
         * code_d : 100
         * code_n : 104
         * txt_d : 晴
         * txt_n : 阴
         */

        private String code_d;
        private String code_n;
        @SerializedName("txt_d")
        public String info;
        private String txt_n;

        public String getCode_d() {
            return code_d;
        }

        public void setCode_d(String code_d) {
            this.code_d = code_d;
        }

        public String getCode_n() {
            return code_n;
        }

        public void setCode_n(String code_n) {
            this.code_n = code_n;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getTxt_n() {
            return txt_n;
        }

        public void setTxt_n(String txt_n) {
            this.txt_n = txt_n;
        }
    }

    public static class TmpBean {
        /**
         * max : 4
         * min : -4
         */

        public String max;
        public String min;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }

    public static class WindBean {
        /**
         * deg : 158
         * dir : 南风
         * sc : 微风
         * spd : 5
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
