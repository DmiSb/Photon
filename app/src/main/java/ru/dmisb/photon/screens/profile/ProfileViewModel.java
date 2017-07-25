package ru.dmisb.photon.screens.profile;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class ProfileViewModel extends BaseObservable {
    private boolean isSigned;

    @Bindable
    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
        notifyChange();
    }
}
