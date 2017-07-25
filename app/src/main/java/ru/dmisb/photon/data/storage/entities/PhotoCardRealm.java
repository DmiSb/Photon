package ru.dmisb.photon.data.storage.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.dmisb.photon.data.network.res.PhotoCardRes;

@SuppressWarnings("unused")
public class PhotoCardRealm extends RealmObject {

    @PrimaryKey
    private String id;
    private String owner;
    private String title;
    private String photo;
    private int views;
    private int favorits;
    private RealmList<TagRealm> tags;
    private FilterRealm filter;

    public PhotoCardRealm() {
    }

    public PhotoCardRealm(PhotoCardRes photoCardRes) {
        this.id = photoCardRes.getId();
        this.owner = photoCardRes.getOwner();
        this.title = photoCardRes.getTitle();

        String photoPath = photoCardRes.getPhoto();
        photoPath = photoPath.replace(":8000", ":5000");
        this.photo = photoPath;
        this.views = photoCardRes.getViews();
        this.favorits = photoCardRes.getFavorits();
        this.tags = new RealmList<>();
    }

    //region ================= Getters =================

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

    public String getViewsStr() {
        return String.valueOf(views);
    }

    public int getFavorits() {
        return favorits;
    }

    public String getFavoritsStr() {
        return String.valueOf(favorits);
    }

    public RealmList<TagRealm> getTags() {
        return tags;
    }

    public FilterRealm getFilter() {
        return filter;
    }

    //endregion

    //region ================= Setters =================

    public void setFilter(FilterRealm filter) {
        this.filter = filter;
    }

    //endregion
}
