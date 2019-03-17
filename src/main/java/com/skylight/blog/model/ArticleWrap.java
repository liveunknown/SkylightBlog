package com.skylight.blog.model;

import java.util.List;

public class ArticleWrap {
    private Long articleInfoId;
    private Long categoryId;
    private Category category;
    private String title;
    private String summary;
    private int views;
    private String createBy;
    private String modifiedBy;
    private int isDeleted;

    private ArticleContent articleContent;

    private List<Comment> commentList;

    @Override
    public String toString() {
        return "ArticleWrap{" +
                "articleInfoId=" + articleInfoId +
                ", categoryId=" + categoryId +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", views=" + views +
                ", createBy='" + createBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", isDeleted=" + isDeleted +
                ", articleContent=" + articleContent +
                ", commentList=" + commentList +
                '}';
    }

    public Long getArticleInfoId() {
        return articleInfoId;
    }

    public void setArticleInfoId(Long articleInfoId) {
        this.articleInfoId = articleInfoId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public ArticleContent getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(ArticleContent articleContent) {
        this.articleContent = articleContent;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }
}
