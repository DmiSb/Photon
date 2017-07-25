package ru.dmisb.photon.screens.main;

@SuppressWarnings("unused")
interface IMainPresenter {
    void onPhotoCardClick(String photoCardId);
    void onAuthClick();
    void onExitClick();
    void onRegisterClick();
    void onSearchClick();

    void checkFilter();
}
