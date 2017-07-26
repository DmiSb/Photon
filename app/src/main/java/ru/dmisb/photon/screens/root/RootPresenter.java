package ru.dmisb.photon.screens.root;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;

import javax.inject.Inject;

import mortar.Presenter;
import mortar.bundler.BundleService;
import ru.dmisb.photon.core.IBarView;
import ru.dmisb.photon.data.network.err.ErrorRes;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.ui.helpers.ActionHolder;
import ru.dmisb.photon.ui.helpers.BarBuilder;

@SuppressWarnings("unused")
public class RootPresenter extends Presenter<IRootView> implements IRootPresenter {

    @Inject
    RootModel model;

    public RootPresenter() {
        initComponent();
    }

    @Override
    protected BundleService extractBundleService(IRootView view) {
        return BundleService.getBundleService((RootActivity) view);
    }

    //region ================= IRootPresenter =================

    @Override
    public void showMessage(String message) {
        if (getView() != null)
            ((RootActivity) getView()).showMessage(message);
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        if (getView() != null)
            ((RootActivity) getView()).showMessage(messageResId);
    }

    @Override
    public void showError(Throwable throwable) {
        if (getView() != null) {
            if (throwable instanceof ErrorRes)
                showMessage(((ErrorRes) throwable).getType().getMessageResId());
            else
                ((RootActivity) getView()).showMessage(throwable.getMessage());
        }
    }

    @Override
    public void showActionMessage(ActionHolder action) {
        if (getView() != null)
            getView().showActionMessage(action);
    }

    @Override
    public void showProgress() {
        if (getView() != null)
            ((RootActivity) getView()).showProgress();
    }

    @Override
    public void hideProgress() {
        if (getView() != null)
            ((RootActivity) getView()).hideProgress();
    }

    @Override
    public BarBuilder newBarBuilder() {
        return getView() != null ? new BarBuilder((IBarView) getView()) : null;
    }

    @Override
    public boolean checkPermissions(@NonNull String[] permissions) {
        if (getView() != null) {
            for (String permission : permissions) {
                int selfPermission = ContextCompat.checkSelfPermission((RootActivity) getView(), permission);
                if (selfPermission != PackageManager.PERMISSION_GRANTED)
                    return false;

            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void startIntent(Intent intent, int requestCode) {
        if (getView() != null)
            ((RootActivity) getView()).startActivityForResult(intent, requestCode);
    }

    @Override
    public void showMainScreen() {
        if (getView() != null)
            getView().showMainScreen();
    }

    @Override
    public void showProfileScreen(int idx) {
        if (getView() != null)
            getView().showProfileScreen(idx);
    }

    @Override
    public void showUploadScreen(String albumId) {
        if (getView() != null)
            getView().showUploadScreen(albumId);
    }

    @Override
    public void showPhotoCardScreen(String photoCardId) {
        if (getView() != null)
            getView().showPhotoCardScreen(photoCardId);
    }

    @Override
    public void showSearchScreen() {
        if (getView() != null)
            getView().showSearchScreen();
    }

    @Override
    public void showAlbumScreen(String id, int idx) {
        if (getView() != null)
            getView().showAlbumScreen(id, idx);
    }

    @Override
    public void showNewCardScreen(String albumId, String photoUri) {
        if (getView() != null)
            getView().showNewCardScreen(albumId, photoUri);
    }

    //endregion

    //region ================= DI =================

    private void initComponent() {
        RootComponent rootComponent = ScreenScoper.getComponent(ScreenScoper.ROOT_SCOPE_NAME);
        if (rootComponent != null)
            rootComponent.inject(this);
    }

    //endregion
}