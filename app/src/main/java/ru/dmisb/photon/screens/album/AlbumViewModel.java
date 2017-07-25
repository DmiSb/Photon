package ru.dmisb.photon.screens.album;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import ru.dmisb.photon.BR;

public class AlbumViewModel extends BaseObservable {
    private String id;
    private String title;
    private String description;
    private String cardsCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public String getCardsCount() {
        return cardsCount;
    }

    public void setCardsCount(String cardsCount) {
        this.cardsCount = cardsCount;
        notifyPropertyChanged(BR.cardsCount);
    }
}
