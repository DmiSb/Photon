package ru.dmisb.photon.data.storage.entities;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.dmisb.photon.data.network.res.AlbumRes;
import ru.dmisb.photon.data.network.res.PhotoCardRes;

public class AlbumRealm extends RealmObject {
    @PrimaryKey
    private String id;
    private String owner;
    private String title;
    private String preview;
    private String description;
    private int views;
    private int favorits;
    private boolean active;
    private RealmList<PhotoCardRealm> photocards;

    public AlbumRealm() {
    }

    public AlbumRealm(AlbumRes albumRes) {
        this.id = albumRes.getId();
        this.owner = albumRes.getOwner();
        this.title = albumRes.getTitle();
        this.description = albumRes.getDescription();
        this.views = albumRes.getViews();
        this.favorits = albumRes.getFavorits();
        this.active = albumRes.isActive();
        this.photocards = new RealmList<>();

        List<PhotoCardRes> photoCardList = albumRes.getPhotocards();
        if (photoCardList.size() > 0) {
            for (PhotoCardRes photoCard : photoCardList) {
                if (photoCard.isActive() && photoCard.getPhoto() != null && !photoCard.getPhoto().isEmpty()) {
                    this.preview = photoCard.getPhoto();
                    break;
                }
            }
        }
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

    public String getPreview() {
        return preview;
    }

    public String getDescription() {
        return description;
    }

    public int getViews() {
        return views;
    }

    public String getViewsStr() {
        return String.valueOf(views);
    }

    public int getFavorits() {
        return favorits;
    }

    public String getFavoritsStr() {
        return String.valueOf(favorits);
    }

    public boolean isActive() {
        return active;
    }

    public RealmList<PhotoCardRealm> getPhotocards() {
        return photocards;
    }

    public String getPhotoCardCount() {
        return String.valueOf(photocards.size());
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
