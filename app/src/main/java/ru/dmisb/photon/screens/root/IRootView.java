package ru.dmisb.photon.screens.root;

import android.net.Uri;

import ru.dmisb.photon.ui.helpers.ActionHolder;

interface IRootView {
    void showActionMessage(ActionHolder action);

    void showMainScreen();
    void showProfileScreen(int idx);
    void showUploadScreen(String albumId);

    void showPhotoCardScreen(String photoCardId);
    void showSearchScreen();

    void showAlbumScreen(String id, int idx);
    void showNewCardScreen(String albumId, Uri photoUri);
}
