package ru.dmisb.photon.screens.upload;

import android.content.Intent;

public interface IUploadPresenter {
    void onSelectButtonClick();
    void onActivityResult(int requestCode, Intent data);
}
