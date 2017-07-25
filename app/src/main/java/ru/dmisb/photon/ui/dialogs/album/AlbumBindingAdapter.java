package ru.dmisb.photon.ui.dialogs.album;

import android.databinding.BindingAdapter;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.RelativeLayout;

import ru.dmisb.photon.R;

public class AlbumBindingAdapter {
    @BindingAdapter("validName")
    public static void getValidName(RelativeLayout layout, AlbumViewModel model) {
        if (model != null && model.isNameValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }

    @BindingAdapter("validDescription")
    public static void getValidDescription(RelativeLayout layout, AlbumViewModel model) {
        if (model != null && model.isDescriptionValid())
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.valid_border));
        else
            layout.setBackground(ContextCompat.getDrawable(layout.getContext(), R.drawable.invalid_border));
    }

    @BindingAdapter("canAdd")
    public static void getCanAdd(Button button, AlbumViewModel model) {
        if (model != null && model.isNameValid() && model.isDescriptionValid())
            button.setEnabled(true);
        else
            button.setEnabled(false);
    }
}
