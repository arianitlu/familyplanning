package com.example.rinor.familyplanning.model;

public class Institution {

    private String name;
    private String description;
    private String image;
    private String logo;

    private String services;
    private String website;

    public Institution(String name, String description, String image, String logo, String services, String website) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.logo = logo;
        this.services = services;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getLogo() {
        return logo;
    }

    public String getServices() {
        return services;
    }

    public String getWebsite() {
        return website;
    }
}
