package com.coviam.QuestionMicroservice.dto;

public class CategoryDTO {

    private  String categoryId;
    private  String categoryName;

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
