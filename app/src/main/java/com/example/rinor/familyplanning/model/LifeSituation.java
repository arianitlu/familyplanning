package com.example.rinor.familyplanning.model;

public class LifeSituation {

    private int id;
    private String lifeSituationName;
    private String icon;
    private int languageID;
    private int institutionCategory;
    private int statusi;

    public LifeSituation(int id, String lifeSituationName, int languageID, int statusi) {
        this.id = id;
        this.lifeSituationName = lifeSituationName;
        this.languageID = languageID;
        this.statusi = statusi;
    }

    public LifeSituation(int id, String lifeSituationName, int languageID, int institutionCategory, int statusi) {
        this.id = id;
        this.lifeSituationName = lifeSituationName;
        this.languageID = languageID;
        this.institutionCategory = institutionCategory;
        this.statusi = statusi;
    }

    public LifeSituation(int id, String lifeSituationName, String icon, int languageID, int institutionCategory, int statusi) {

        this.id = id;
        this.lifeSituationName = lifeSituationName;
        this.icon = icon;
        this.languageID = languageID;
        this.institutionCategory = institutionCategory;
        this.statusi = statusi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLifeSituationName() {
        return lifeSituationName;
    }

    public void setLifeSituationName(String lifeSituationName) {
        this.lifeSituationName = lifeSituationName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public int getInstitutionCategory() {
        return institutionCategory;
    }

    public void setInstitutionCategory(int institutionCategory) {
        this.institutionCategory = institutionCategory;
    }

    public int getStatusi() {
        return statusi;
    }

    public void setStatusi(int statusi) {
        this.statusi = statusi;
    }


}
