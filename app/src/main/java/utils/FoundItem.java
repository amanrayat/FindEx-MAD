package utils;

import com.example.findex.CategoryEnum;
import com.example.findex.LocationEnum;
import java.util.Date;
import java.util.Locale;

public class FoundItem {
    Long id;
    String imageUrl;
    String title;
    String description;
    Date date;
    LocationEnum location;
    CategoryEnum category;
    // Add an enum for a place
    // Add enum for category

    public FoundItem(Long id, int imageResID, String title, String description, CategoryEnum category,
                     LocationEnum location, Date date) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.date = date;
    }

    public FoundItem() {
    }
    public LocationEnum getLocation() {
        return location;
    }

    public void setLocation(LocationEnum location) {
        this.location = location;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getimageUrl() {
        return imageUrl;
    }

    public void setimageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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


