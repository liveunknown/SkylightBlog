package com.skylight.blog.model;

public class UpdateLog {
    private int id;
    private String updateDate;
    private String updateContent;

    @Override
    public String toString() {
        return "UpdateLog{" +
                "id=" + id +
                ", updateDate='" + updateDate + '\'' +
                ", updateContent='" + updateContent + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent;
    }
}
