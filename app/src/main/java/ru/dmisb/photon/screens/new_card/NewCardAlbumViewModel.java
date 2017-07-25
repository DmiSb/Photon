package ru.dmisb.photon.screens.new_card;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ru.dmisb.photon.data.storage.entities.AlbumRealm;

@SuppressWarnings("unused")
public class NewCardAlbumViewModel extends BaseObservable {
    private String id;
    private String title;
    private String preview;
    private String cardCount;
    private int favorites;
    private int views;
    private boolean isSelected;

    NewCardAlbumViewModel(AlbumRealm album) {
        this.id = album.getId();
        this.title = album.getTitle();
        this.preview = album.getPreview();
        this.cardCount = album.getPhotoCardCount();
        this.favorites = album.getFavorits();
        this.views = album.getViews();
        this.isSelected = false;
        notifyChange();
    }

    public String getId() {
        return id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public String getPreview() {
        return preview;
    }

    @Bindable
    public String getCardCount() {
        return cardCount;
    }

    @Bindable
    public String getFavorites() {
        return String.valueOf(favorites);
    }

    @Bindable
    public String getViews() {
        return String.valueOf(views);
    }

    @Bindable
    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        notifyChange();
    }
}
