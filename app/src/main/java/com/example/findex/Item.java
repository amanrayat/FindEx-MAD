package com.example.findex;


public class Item {
    public Item(String title, String description, String location, String category, String imageUrl) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Item() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private String title;
    private String description;
    private String location;
    private String category;
    private String imageUrl;

}
