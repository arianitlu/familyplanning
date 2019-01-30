package com.example.rinor.familyplanning.model;

public class Institution {

    private int id;
    private String name;
    private String description;
    private String image;
    private String logo;
    private int lat;
    private int lng;
    private String services;
    private String website;

    public Institution(int id, String name, String description, String image, String logo, int lat, int lng, String services, String website) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.logo = logo;
        this.lat = lat;
        this.lng = lng;
        this.services = services;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLng() {
        return lng;
    }

    public void setLng(int lng) {
        this.lng = lng;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
