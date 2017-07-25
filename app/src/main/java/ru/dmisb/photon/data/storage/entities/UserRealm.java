package ru.dmisb.photon.data.storage.entities;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.dmisb.photon.data.network.res.UserRes;

@SuppressWarnings("unused")
public class UserRealm extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String login;
    private String avatar;
    private int albumCount;
    private int photoCardCount;
    private RealmList<AlbumRealm> albums;

    public UserRealm() {
    }

    public UserRealm(UserRes userRes) {
        this.id = userRes.getId();
        this.name = userRes.getName();
        this.login = userRes.getLogin();
        this.avatar = userRes.getAvatar();
        this.albumCount = userRes.getAlbumCount();
        this.photoCardCount = userRes.getPhotoCardCount();
        this.albums = new RealmList<>();
    }

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

    public int getAlbumCount() {
        return albumCount;
    }

    public String getAlbumCountStr() {
        return String.valueOf(albumCount);
    }

    public int getPhotoCardCount() {
        return photoCardCount;
    }

    public String getPhotoCardCountStr() {
        return String.valueOf(photoCardCount);
    }

    public RealmList<AlbumRealm> getAlbums() {
        return albums;
    }

    public List<AlbumRealm> getActiveAlbums() {
        return albums.where().equalTo("active", true).findAll();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
    }

    public void setPhotoCardCount(int photoCardCount) {
        this.photoCardCount = photoCardCount;
    }

    public void setAlbums(RealmList<AlbumRealm> albums) {
        this.albums = albums;
    }
}
