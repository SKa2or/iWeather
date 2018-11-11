package com.example.SKa2or.iWeather;

import java.util.List;

public class WeatherBean {

    /**
     * date : 20180426
     * message : Success !
     * status : 200
     * city : 广州
     * count : 530
     * data : {"shidu":"93%","pm25":50,"pm10":72,"quality":"良","wendu":"20","ganmao":"极少数敏感人群应减少户外活动","yesterday":{"date":"25日星期三","sunrise":"06:00","high":"高温 26.0℃","low":"低温 22.0℃","sunset":"18:52","aqi":39,"fx":"东北风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"},"forecast":[{"date":"26日星期四","sunrise":"05:59","high":"高温 27.0℃","low":"低温 22.0℃","sunset":"18:52","aqi":76,"fx":"无持续风向","fl":"<3级","type":"阵雨","notice":"阵雨来袭，出门记得带伞"},{"date":"27日星期五","sunrise":"05:58","high":"高温 26.0℃","low":"低温 22.0℃","sunset":"18:53","aqi":73,"fx":"无持续风向","fl":"<3级","type":"中雨","notice":"记得随身携带雨伞哦"},{"date":"28日星期六","sunrise":"05:57","high":"高温 26.0℃","low":"低温 22.0℃","sunset":"18:53","aqi":76,"fx":"南风","fl":"3-4级","type":"阵雨","notice":"阵雨来袭，出门记得带伞"},{"date":"29日星期日","sunrise":"05:57","high":"高温 27.0℃","low":"低温 23.0℃","sunset":"18:53","aqi":64,"fx":"东南风","fl":"3-4级","type":"雷阵雨","notice":"带好雨具，别在树下躲雨"},{"date":"30日星期一","sunrise":"05:56","high":"高温 28.0℃","low":"低温 23.0℃","sunset":"18:54","aqi":55,"fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]}
     */

    private String date;
    private String message;
    private int status;
    private String city;
    private int count;
    private DataBean data;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shidu : 93%
         * pm25 : 50
         * pm10 : 72
         * quality : 良
         * wendu : 20
         * ganmao : 极少数敏感人群应减少户外活动
         * yesterday : {"date":"25日星期三","sunrise":"06:00","high":"高温 26.0℃","low":"低温 22.0℃","sunset":"18:52","aqi":39,"fx":"东北风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}
         * forecast : [{"date":"26日星期四","sunrise":"05:59","high":"高温 27.0℃","low":"低温 22.0℃","sunset":"18:52","aqi":76,"fx":"无持续风向","fl":"<3级","type":"阵雨","notice":"阵雨来袭，出门记得带伞"},{"date":"27日星期五","sunrise":"05:58","high":"高温 26.0℃","low":"低温 22.0℃","sunset":"18:53","aqi":73,"fx":"无持续风向","fl":"<3级","type":"中雨","notice":"记得随身携带雨伞哦"},{"date":"28日星期六","sunrise":"05:57","high":"高温 26.0℃","low":"低温 22.0℃","sunset":"18:53","aqi":76,"fx":"南风","fl":"3-4级","type":"阵雨","notice":"阵雨来袭，出门记得带伞"},{"date":"29日星期日","sunrise":"05:57","high":"高温 27.0℃","low":"低温 23.0℃","sunset":"18:53","aqi":64,"fx":"东南风","fl":"3-4级","type":"雷阵雨","notice":"带好雨具，别在树下躲雨"},{"date":"30日星期一","sunrise":"05:56","high":"高温 28.0℃","low":"低温 23.0℃","sunset":"18:54","aqi":55,"fx":"东南风","fl":"3-4级","type":"多云","notice":"阴晴之间，谨防紫外线侵扰"}]
         */

        private String shidu;
        private int pm25;
        private int pm10;
        private String quality;
        private String wendu;
        private String ganmao;
        private YesterdayBean yesterday;
        private List<ForecastBean> forecast;

        public String getShidu() {
            return shidu;
        }

        public void setShidu(String shidu) {
            this.shidu = shidu;
        }

        public int getPm25() {
            return pm25;
        }

        public void setPm25(int pm25) {
            this.pm25 = pm25;
        }

        public int getPm10() {
            return pm10;
        }

        public void setPm10(int pm10) {
            this.pm10 = pm10;
        }

        public String getQuality() {
            return quality;
        }

        public void setQuality(String quality) {
            this.quality = quality;
        }

        public String getWendu() {
            return wendu;
        }

        public void setWendu(String wendu) {
            this.wendu = wendu;
        }

        public String getGanmao() {
            return ganmao;
        }

        public void setGanmao(String ganmao) {
            this.ganmao = ganmao;
        }

        public YesterdayBean getYesterday() {
            return yesterday;
        }

        public void setYesterday(YesterdayBean yesterday) {
            this.yesterday = yesterday;
        }

        public List<ForecastBean> getForecast() {
            return forecast;
        }

        public void setForecast(List<ForecastBean> forecast) {
            this.forecast = forecast;
        }

        public static class YesterdayBean {
            /**
             * date : 25日星期三
             * sunrise : 06:00
             * high : 高温 26.0℃
             * low : 低温 22.0℃
             * sunset : 18:52
             * aqi : 39
             * fx : 东北风
             * fl : 3-4级
             * type : 多云
             * notice : 阴晴之间，谨防紫外线侵扰
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }

        public static class ForecastBean {
            /**
             * date : 26日星期四
             * sunrise : 05:59
             * high : 高温 27.0℃
             * low : 低温 22.0℃
             * sunset : 18:52
             * aqi : 76
             * fx : 无持续风向
             * fl : <3级
             * type : 阵雨
             * notice : 阵雨来袭，出门记得带伞
             */

            private String date;
            private String sunrise;
            private String high;
            private String low;
            private String sunset;
            private int aqi;
            private String fx;
            private String fl;
            private String type;
            private String notice;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getSunrise() {
                return sunrise;
            }

            public void setSunrise(String sunrise) {
                this.sunrise = sunrise;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getSunset() {
                return sunset;
            }

            public void setSunset(String sunset) {
                this.sunset = sunset;
            }

            public int getAqi() {
                return aqi;
            }

            public void setAqi(int aqi) {
                this.aqi = aqi;
            }

            public String getFx() {
                return fx;
            }

            public void setFx(String fx) {
                this.fx = fx;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }
        }
    }
}
