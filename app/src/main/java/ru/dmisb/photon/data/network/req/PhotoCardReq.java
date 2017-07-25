package ru.dmisb.photon.data.network.req;

import java.util.List;

import ru.dmisb.photon.data.dto.FilterDto;

@SuppressWarnings("unused")
public class PhotoCardReq {
    private String album;
    private String title;
    private String photo;
    private List<String> tags;
    private FilterDto filters;

    public PhotoCardReq(String title, List<String> tags, FilterDto filters) {
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

    public FilterDto getFilters() {
        return filters;
    }

    public void setFilter(FilterDto filters) {
        this.filters = filters;
    }
}
