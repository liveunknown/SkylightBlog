package com.skylight.blog.model;

public class Friendlink {

    private int id;
    private String blogger;
    private String url;
    private int isFamous;

    @Override
    public String toString() {
        return "Friendlink{" +
                "id=" + id +
                ", blogger='" + blogger + '\'' +
                ", url='" + url + '\'' +
                ", isFamous=" + isFamous +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlogger() {
        return blogger;
    }

    public void setBlogger(String blogger) {
        this.blogger = blogger;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsFamous() {
        return isFamous;
    }

    public void setIsFamous(int isFamous) {
        this.isFamous = isFamous;
    }
}
