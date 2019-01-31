package com.example.rinor.familyplanning.model;

public class Language {

    private int languageId;
    private String languageName;
    private String languageIso;
    private String description;
    private String languageFlag;

    public Language(int languageId, String languageName, String languageIso, String description, String languageFlag) {
        this.languageId = languageId;
        this.languageName = languageName;
        this.languageIso = languageIso;
        this.description = description;
        this.languageFlag = languageFlag;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageIso() {
        return languageIso;
    }

    public void setLanguageIso(String languageIso) {
        this.languageIso = languageIso;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguageFlag() {
        return languageFlag;
    }

    public void setLanguageFlag(String languageFlag) {
        this.languageFlag = languageFlag;
    }
}