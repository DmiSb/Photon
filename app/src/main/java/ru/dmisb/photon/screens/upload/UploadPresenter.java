package ru.dmisb.photon.screens.upload;

import android.Manifest;
import android.content.Intent;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.utils.Constants;

public class UploadPresenter extends BasePresenter<UploadView, UploadModel> implements IUploadPresenter {

    private String albumId;

    public UploadPresenter(String albumId) {
        this.albumId = albumId;
    }

    //region ================= IUploadPresenter =================

    @Override
    public void onSelectButtonClick() {
        if (rootPresenter != null) {
        String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
            if (rootPresenter.checkPermissions(permissions)){
                takePhotoFromGallery();
            }
        }
    }

    private void takePhotoFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        rootPresenter.startIntent(intent, Constants.REQUEST_PHOTO_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, Intent data) {
        if (requestCode == Constants.REQUEST_PHOTO_FROM_GALLERY && data != null) {
            rootPresenter.showNewCardScreen(albumId, data.getData());
        }
    }

    //endregion


    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        UploadScreen.Component component = ScreenScoper.getComponent(ScreenScoper.UPLOAD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initActionBar() {
        if (rootPresenter != null)
            rootPresenter.newBarBuilder()
                .setToolbarVisible(true)
                .setTitleResId(R.string.main_title)
                    .setBottomBarVisible(true)
                    .build();
    }

    //endregion
}
