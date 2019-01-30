package com.example.rinor.familyplanning.model;

public class InstitutionCategory {

    private int ID;
    private int parentID;
    private String categoryName;
    private String Description;
    private String icon;
    private String languageId;

    public InstitutionCategory(int ID, int parentID, String categoryName, String description, String icon, String languageId) {
        this.ID = ID;
        this.parentID = parentID;
        this.categoryName = categoryName;
        Description = description;
        this.icon = icon;
        this.languageId = languageId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }
}
