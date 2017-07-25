package ru.dmisb.photon.screens.profile;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import ru.dmisb.photon.data.storage.entities.UserRealm;

@SuppressWarnings("unused")
public class ProfileBindingAdapter {

    @BindingAdapter("emptyVisible")
    public void getEmptyVisible(TextView textView, UserRealm user) {
        if (user == null)
            return;

        textView.setVisibility(user.getAlbumCount() == 0 ? View.VISIBLE : View.GONE);
    }
}
