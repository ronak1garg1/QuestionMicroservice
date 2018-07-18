package com.coviam.QuestionMicroservice.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = CategoryEntity.TABLE_NAME)
public class CategoryEntity {

    public static final String TABLE_NAME = "CATEGORY";
    public static final String ID_COLUMN = "category_id";

    @Id
    @Column(name= CategoryEntity.ID_COLUMN)
    private  String categoryId;
    private  String categoryName;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "categoryId='" + categoryId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
