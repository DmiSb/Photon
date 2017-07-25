package ru.dmisb.photon.screens.upload;

import android.content.Intent;

@SuppressWarnings("unused")
interface IUploadPresenter {
    void onSelectButtonClick();
    void onActivityResult(int requestCode, Intent data);
}
