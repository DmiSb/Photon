package ru.dmisb.photon.data.network.req;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("unused")
public class PhotoCardReq implements Serializable {
    private String album;
    private String title;
    private String photo;
    private List<String> tags;
    private FilterReq filters;

    public PhotoCardReq(String albumId, String title, List<String> tags, FilterReq filters) {
        this.album = albumId;
        this.title = title;
        this.tags = tags;
        this.filters = filters;
    }

    public String getAlbum() {
        return album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public FilterReq getFilters() {
        return filters;
    }

    public void setFilter(FilterReq filters) {
        this.filters = filters;
    }
}
