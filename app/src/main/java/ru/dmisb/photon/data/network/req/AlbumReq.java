package ru.dmisb.photon.data.network.req;

public class AlbumReq {
    private String owner;
    private String title;
    private String preview;
    private String description;

    public AlbumReq(String owner, String title, String preview, String description) {
        this.owner = owner;
        this.title = title;
        this.preview = preview;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
