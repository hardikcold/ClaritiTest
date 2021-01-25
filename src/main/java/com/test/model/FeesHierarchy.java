package com.test.model;

public class FeesHierarchy {

    private String department;
    private String category;
    private String subCategory;
    private String type;

    public FeesHierarchy(String department, String category, String subCategory, String type){
        this.department = department;
        this.category = category;
        this.subCategory = subCategory;
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FeesHierarchy{" +
                "department='" + department + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
