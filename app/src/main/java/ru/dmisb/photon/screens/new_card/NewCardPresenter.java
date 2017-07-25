package ru.dmisb.photon.screens.new_card;

import android.net.Uri;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.screens.selector.filter.FilterViewModel;

public class NewCardPresenter
        extends BasePresenter<NewCardView, NewCardModel> implements INewCardPresenter {

    private NewCardViewModel viewModel = new NewCardViewModel();
    private FilterViewModel filterViewModel = new FilterViewModel();
    private String albumId;
    private Uri photoUri;

    NewCardPresenter(String albumId, Uri photoUri) {
        this.albumId = albumId;
        this.photoUri = photoUri;
    }

    //region ================= INewCardPresenter =================


    @Override
    public void onTitleClearClick() {
        viewModel.setTitle("");
    }

    @Override
    public void onTagAddClick() {
        viewModel.addTag(viewModel.getTag());
        viewModel.setTag("");
    }

    @Override
    public void onTagClearClick() {
        viewModel.setTag("");
    }

    @Override
    public void onAddAlbumClick() {

    }

    @Override
    public void onOkClick() {

    }

    @Override
    public void onCancelClick() {
        rootPresenter.showUploadScreen(albumId);
    }

    //endregion


    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        NewCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.NEW_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        if (getView() != null) {
            getView().setViewModel(viewModel, filterViewModel);

            model.getUser()
                    .subscribe(
                            userRealm -> {
                                int albumCount = userRealm.getAlbums().size();
                                if (albumCount >0) {
                                    viewModel.setAlbumCount(albumCount);
                                    for (AlbumRealm album : userRealm.getAlbums()) {
                                        if (album.isActive())
                                            getView().addAlbim( new NewCardAlbum(album) );
                                    }
                                }
                            },
                            throwable -> {}
                    );
        }
    }

    @Override
    protected void initActionBar() {
        rootPresenter.newBarBuilder()
                .setToolbarVisible(true)
                .setBottomBarVisible(false)
                .setBackArrow(true)
                .setTitleResId(R.string.main_title)
                .build();
    }

    //endregion
}
