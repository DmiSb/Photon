package ru.dmisb.photon.screens.new_card;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.util.ArrayList;
import java.util.List;

import ru.dmisb.photon.BR;

public class NewCardViewModel extends BaseObservable {
    private String title;
    private String tag;
    private List<String> tags = new ArrayList<>();
    private int albumCount;

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }

    @Bindable
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
        notifyPropertyChanged(BR.tag);
    }

    void addTag(String tag) {
        tags.add(tag);
    }

    List<String> getTags() {
        return tags;
    }

    @Bindable
    public int getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
        notifyChange();
    }
}
