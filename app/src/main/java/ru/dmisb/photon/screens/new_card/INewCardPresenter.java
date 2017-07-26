package ru.dmisb.photon.screens.new_card;

@SuppressWarnings("unused")
interface INewCardPresenter {
    void onTitleClearClick();

    void onTagAddClick();
    void onTagClearClick();

    void onAddAlbumClick();

    void onOkClick();
    void onCancelClick();

    void onAlbumSelected(String albumId);
}
