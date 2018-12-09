package com.bwei.zhoukaolianxi02.bean;

import java.util.List;

public class TuPianBean {


    /**
     * msg : 成功的返回
     * code : 1
     * data : [{"uniquekey":"5eb6399ece021029a1a25bbfcdfdfbb4","title":"勇士超强战术遇难题，高效打法第一又何妨？恰好撞火箭防守铁桶阵","date":"2018-05-12 09:38","category":"体育","author_name":"凤凰网","url":"http://mini.eastday.com/mobile/180512093818317.html","img":"http://09.imgmini.eastday.com/mobile/20180512/20180512093818_afba29fb6a2116e29ea41721c8a68f75_1_mwpm_03200403.jpg"},{"uniquekey":"d778396d128e84d6a8f0d7715ff2da7e","title":"英超夏季转会期提前关闭，波切蒂诺：不是好主意","date":"2018-05-12 09:39","category":"体育","author_name":"肆客足球","url":"http://mini.eastday.com/mobile/180512093955457.html","img":"http://02.imgmini.eastday.com/mobile/20180512/20180512093955_e8e3243f864d58b4cc5148af15a22a43_2_mwpm_03200403.jpg"},{"uniquekey":"2e84edb8daaa33565a9e8113982771d7","title":"库里：保罗曾是我的导师，他教给我如何在联盟出人头地","date":"2018-05-12 09:39","category":"体育","author_name":"虎扑体育","url":"http://mini.eastday.com/mobile/180512093955335.html","img":"http://07.imgmini.eastday.com/mobile/20180512/20180512093955_0e51fb1f7fc2ac5e86cd0cd91206aa26_1_mwpm_03200403.jpg"},{"uniquekey":"bbaf0f5e67d1b6931689d9bb7326050f","title":"乔治 - 希尔获得学士学位，将在东决前参加毕业典礼","date":"2018-05-12 09:39","category":"体育","author_name":"虎扑体育","url":"http://mini.eastday.com/mobile/180512093952808.html","img":"http://09.imgmini.eastday.com/mobile/20180512/20180512093952_70839b1039313eabdc9b96885d99714e_1_mwpm_03200403.jpg"}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uniquekey : 5eb6399ece021029a1a25bbfcdfdfbb4
         * title : 勇士超强战术遇难题，高效打法第一又何妨？恰好撞火箭防守铁桶阵
         * date : 2018-05-12 09:38
         * category : 体育
         * author_name : 凤凰网
         * url : http://mini.eastday.com/mobile/180512093818317.html
         * img : http://09.imgmini.eastday.com/mobile/20180512/20180512093818_afba29fb6a2116e29ea41721c8a68f75_1_mwpm_03200403.jpg
         */

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String img;

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }
}
