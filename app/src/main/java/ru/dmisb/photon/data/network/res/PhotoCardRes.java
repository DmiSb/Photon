package ru.dmisb.photon.data.network.res;

import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class PhotoCardRes {
    private String id;
    private String owner;
    private String title;
    private String photo;
    private int views;
    private int favorits;
    private boolean active;
    private Date updated;
    private Date created;

    private List<String> tags;
    private FilterRes filters;

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getPhoto() {
        return photo;
    }

    public int getViews() {
        return views;
    }

    public int getFavorits() {
        return favorits;
    }

    public boolean isActive() {
        return active;
    }

    public Date getUpdated() {
        return updated;
    }

    public Date getCreated() {
        return created;
    }

    public List<String> getTags() {
        return tags;
    }

    public FilterRes getFilter() {
        return filters;
    }

}
