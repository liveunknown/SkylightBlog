package com.skylight.blog.model;

public class ArticleLabel {

    private Long id;
    private Long articleId;
    private Long labelId;
    private String createBy;
    private String modifiedBy;

    private Label label;

    @Override
    public String toString() {
        return "ArticleLabel{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", labelId=" + labelId +
                ", createBy='" + createBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", label=" + label +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getLabelId() {
        return labelId;
    }

    public void setLabelId(Long labelId) {
        this.labelId = labelId;
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

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
