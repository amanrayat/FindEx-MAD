package utils;

import java.util.Date;

public class FoundItem {
    Long id;
    int imageResID;
    String title;
    String description;
    Date date;
    // Add an enum for a place
    public FoundItem(Long id, int imageResID, String title, String description, Date date) {
        this.id = id;
        this.imageResID = imageResID;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getImageResID() {
        return imageResID;
    }

    public void setImageResID(int imageResID) {
        this.imageResID = imageResID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




}


