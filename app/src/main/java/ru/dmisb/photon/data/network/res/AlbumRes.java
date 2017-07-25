package ru.dmisb.photon.data.network.res;

import java.util.List;

public class AlbumRes {
    private String id;
    private String owner;
    private String title;
    private String description;
    private int views;
    private int favorits;
    private boolean active;
    private List<PhotoCardRes> photocards;

    public AlbumRes(String id, String owner, String title, String description) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public List<PhotoCardRes> getPhotocards() {
        return photocards;
    }
}
