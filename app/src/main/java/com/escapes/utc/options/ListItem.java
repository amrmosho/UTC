package com.escapes.utc.options;

/**
 * Created by karma on 02/05/2015.
 */
public class ListItem {

    private String headline;
    private String listDes;
    private String date;
    private String url;
    private String id;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getListDes() {
        return listDes;
    }

    public void setListDes(String listDes) {
        this.listDes = listDes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "[ headline=" + headline + ", reporter Name=" + listDes + " , date=" + date + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
