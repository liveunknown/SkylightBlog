package com.skylight.blog.model;

public class ArticleInfo {
    private Long id;
    private Long categoryId;
    private String title;
    private String author;
    private String url;
    private String summary;
    private int views;
    private String createBy;
    private String modifiedBy;
    private int isOriginal;
    private int isDeleted;

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", url='" + url + '\'' +
                ", summary='" + summary + '\'' +
                ", views=" + views +
                ", createBy='" + createBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", isOriginal=" + isOriginal +
                ", isDeleted=" + isDeleted +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(int isOriginal) {
        this.isOriginal = isOriginal;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }
}
