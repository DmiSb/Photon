package ru.dmisb.photon.screens.root;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import ru.dmisb.photon.ui.helpers.ActionHolder;
import ru.dmisb.photon.ui.helpers.BarBuilder;

@SuppressWarnings("unused")
public interface IRootPresenter {
    void showMessage(String message);
    void showMessage(@StringRes int messageResId);
    void showError(Throwable throwable);
    void showActionMessage(ActionHolder action);

    void showProgress();
    void hideProgress();

    BarBuilder newBarBuilder();

    boolean checkPermissions(@NonNull String[] permissions);
    void startIntent(Intent intent, int requestCode);

    void showMainScreen();
    void showProfileScreen(int idx);
    void showUploadScreen(String albumId);

    void showPhotoCardScreen(String photoCardId);
    void showSearchScreen();

    void showAlbumScreen(String id, int idx);
    void showNewCardScreen(String albumId, String photoUri);
}
