package com.example.rinor.familyplanning.model;

public class Topics  {

    private int id;
    private int languageid;
    private String topicName;
    private String  topicCategory;
    private String description;
    private int userid;

    public Topics(int id, int languageid, String topicName, String topicCategory, String description, int userid) {
        this.id = id;
        this.languageid = languageid;
        this.topicName = topicName;
        this.topicCategory = topicCategory;
        this.description = description;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLanguageid() {
        return languageid;
    }

    public void setLanguageid(int languageid) {
        this.languageid = languageid;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicCategory() {
        return topicCategory;
    }

    public void setTopicCategory(String topicCategory) {
        this.topicCategory = topicCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}


