package utils;

import java.util.Date;

public class LostItem {
    Long id;
    String title;
    String description;
    Date date;
    int category;
    // Add an enum for a place
    // Add enum for category

    public LostItem(Long id, String title, String description, Date date, int category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
