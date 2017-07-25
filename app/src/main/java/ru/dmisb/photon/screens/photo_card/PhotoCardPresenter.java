package ru.dmisb.photon.screens.photo_card;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.flow.ScreenScoper;

public class PhotoCardPresenter
        extends BasePresenter<PhotoCardView, PhotoCardModel> {

    private String photoCardId;
    private boolean isLoaded;

    PhotoCardPresenter(String photoCardId) {
        this.photoCardId = photoCardId;
    }

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        PhotoCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PHOTO_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        if (getView() != null) {
            PhotoCardRealm photoCard = model.getPhotoCard(photoCardId);
            getView().setViewModel(photoCard);

            String userId = photoCard.getOwner();
            model.getUserFromNetwork(userId)
                    .subscribe(
                            userRealm -> {
                                getView().bindUser(userRealm);
                                isLoaded = true;
                            },
                            throwable -> rootPresenter.showError(throwable),
                            () -> {
                                if (!isLoaded) {
                                    loadUserFromStorage(userId);
                                }
                            }
                    );
        }
    }

    private void loadUserFromStorage(String userId) {
        if (getView() != null) {
            model.getUserFromStorage(userId)
                    .subscribe(
                            userRealm -> getView().bindUser(userRealm),
                            throwable -> rootPresenter.showError(throwable)
                    );
        }
    }

    @Override
    protected void initActionBar() {
        if (rootPresenter != null)
            rootPresenter.newBarBuilder()
                    .setToolbarVisible(true)
                    .setBottomBarVisible(true)
                    .setBackArrow(true)
                    .setTitleResId(R.string.photo_card_title)
                    .build();
    }
    //endregion
}