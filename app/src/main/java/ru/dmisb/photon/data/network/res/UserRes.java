package ru.dmisb.photon.data.network.res;

import java.util.List;

@SuppressWarnings("unused")
public class UserRes {
    private String id;
    private String name;
    private String login;
    private String avatar;
    private String token;
    private int albumCount;
    private int photocardCount;
    private List<AlbumRes> albums;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getToken() {
        return token;
    }

    public int getAlbumCount() {
        return albumCount;
    }

    public int getPhotoCardCount() {
        return photocardCount;
    }

    public List<AlbumRes> getAlbums() {
        return albums;
    }

    public void setId(String id) {
        this.id = id;
    }
}
