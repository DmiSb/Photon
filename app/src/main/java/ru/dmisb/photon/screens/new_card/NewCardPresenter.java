package ru.dmisb.photon.screens.new_card;

import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.data.network.req.FilterReq;
import ru.dmisb.photon.data.network.req.PhotoCardReq;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.flow.ScreenScoper;

@SuppressWarnings("unused")
public class NewCardPresenter
        extends BasePresenter<NewCardView, NewCardModel> implements INewCardPresenter {

    private NewCardViewModel viewModel = new NewCardViewModel();
    private String albumId;
    private String photoUri;

    NewCardPresenter(String albumId, String photoUri) {
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
        if (!albumId.isEmpty()) {
            FilterReq filter = new FilterReq();
            filter.setDish(viewModel.getDish());
            filter.setNuances(viewModel.getNuances());
            filter.setDecor(viewModel.getDecor());
            filter.setTemperature(viewModel.getTemperature());
            filter.setLight(viewModel.getLight());
            filter.setLightDirection(viewModel.getLightDirection());
            filter.setLightSource(viewModel.getLightSource());

            PhotoCardReq photoCard = new PhotoCardReq(albumId, viewModel.getTitle(), viewModel.getTags(), filter);

            model.saveImage(photoCard, photoUri);
            rootPresenter.showUploadScreen(albumId);
        } else {
            rootPresenter.showMessage(R.string.new_card_album_not_selected);
        }
    }

    @Override
    public void onCancelClick() {
        rootPresenter.showUploadScreen(albumId);
    }

    @Override
    public void onAlbumSelected(String albumId) {
        this.albumId = albumId;
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
            getView().setViewModel(viewModel);

            model.getUser()
                    .subscribe(
                            userRealm -> {
                                int albumCount = userRealm.getAlbums().size();
                                if (albumCount >0) {
                                    viewModel.setAlbumCount(albumCount);
                                    for (AlbumRealm album : userRealm.getAlbums()) {
                                        if (album.isActive())
                                            getView().addAlbim( new NewCardAlbumViewModel(album) );
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
