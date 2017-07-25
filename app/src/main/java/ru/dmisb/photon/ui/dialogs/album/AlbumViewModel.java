package ru.dmisb.photon.ui.dialogs.album;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;

import java.util.regex.Pattern;

import ru.dmisb.photon.utils.Constants;

public class AlbumViewModel extends BaseObservable {
    private String name;
    private String description;

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public boolean isNameValid() {
        return !TextUtils.isEmpty(name) && Pattern.matches(Constants.NAME_PATTERN, name);
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    @Bindable
    public boolean isDescriptionValid() {
        return !TextUtils.isEmpty(description) &&
                description.length() >= Constants.DESCRIPTION_MIN_LENGTH &&
                description.length() <= Constants.DESCRIPTION_MAX_LENGTH;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyChange();
    }
}
