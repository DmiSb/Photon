package ru.dmisb.photon.di.components;

import dagger.Subcomponent;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.modules.RootModule;
import ru.dmisb.photon.screens.album.AlbumScreen;
import ru.dmisb.photon.screens.new_card.NewCardScreen;
import ru.dmisb.photon.screens.upload.UploadScreen;
import ru.dmisb.photon.ui.dialogs.album.AlbumAddDialog;
import ru.dmisb.photon.ui.dialogs.album.AlbumEditDialog;
import ru.dmisb.photon.ui.dialogs.auth.AuthDialog;
import ru.dmisb.photon.screens.main.MainScreen;
import ru.dmisb.photon.screens.photo_card.PhotoCardScreen;
import ru.dmisb.photon.screens.profile.ProfileScreen;
import ru.dmisb.photon.ui.dialogs.register.RegisterDialog;
import ru.dmisb.photon.screens.selector.SelectorScreen;
import ru.dmisb.photon.screens.root.RootActivity;
import ru.dmisb.photon.screens.root.RootModel;
import ru.dmisb.photon.screens.root.RootPresenter;

@Subcomponent(modules = RootModule.class)
@DaggerScope(RootActivity.class)
public interface RootComponent {
    void inject(RootActivity activity);
    void inject(RootPresenter presenter);
    void inject(RootModel model);

    MainScreen.Component plus(MainScreen.Module module);
    PhotoCardScreen.Component plus(PhotoCardScreen.Module module);
    ProfileScreen.Component plus(ProfileScreen.Module module);
    SelectorScreen.Component plus(SelectorScreen.Module module);

    AlbumScreen.Component plus(AlbumScreen.Module module);
    UploadScreen.Component plus(UploadScreen.Module module);
    NewCardScreen.Component plus(NewCardScreen.Module module);

    AuthDialog.Component plusAuthComponent();
    RegisterDialog.Component plusRegisterComponent();

    AlbumAddDialog.Component plusAlbumAddComponent();
    AlbumEditDialog.Component plusAlbumEditComponent();
}