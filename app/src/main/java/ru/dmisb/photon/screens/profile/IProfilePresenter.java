package ru.dmisb.photon.screens.profile;

import ru.dmisb.photon.data.storage.entities.AlbumRealm;

public interface IProfilePresenter {
    void onAuthClick();
    void onRegisterClick();
    void onAlbumAddClick();
    void onAlbumDeleteClick(String albumId);
    void onAlbumShowClick(String albumId, int position);
    void onAlbumEditClick(AlbumRealm album);
}
