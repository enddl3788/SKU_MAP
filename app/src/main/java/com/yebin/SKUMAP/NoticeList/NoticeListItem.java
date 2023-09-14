package com.yebin.SKUMAP.NoticeList;

public class NoticeListItem {
    private String main, sub;

    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }

    public String getSub() {
        return sub;
    }
    public void setSub(String sub) {
        this.sub = sub;
    }

    /*
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
*/
    public NoticeListItem(String main, String sub) {
        this.main = main;
        this.sub = sub;
        //this.url = url;
    }
}