package ru.dmisb.photon.screens.album;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

@Screen(R.layout.screen_album)
public class AlbumScreen extends BaseScreen<RootComponent> {
    private String albumId;
    private int position;

    public AlbumScreen(String albumId, int position) {
        this.albumId = albumId;
        this.position = position;
    }

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.ALBUM_SCOPE_NAME;
    }

    @Override
    public String getParentScopeName() {
        return ScreenScoper.ROOT_SCOPE_NAME;
    }

    @Override
    public Object createScreenComponent(RootComponent parentComponent) {
        return parentComponent.plus(new Module());
    }

    //endregion

    //region ================= DI =================

    @dagger.Module
    public class Module {
        @Provides
        @DaggerScope(AlbumScreen.class)
        AlbumPresenter provideAlbumPresenter() {
           return new AlbumPresenter(albumId, position);
        }

        @Provides
        @DaggerScope(AlbumScreen.class)
        AlbumModel provideAlbumModel() {
           return new AlbumModel();
        }
    }

    @dagger.Subcomponent(modules = AlbumScreen.Module.class)
    @DaggerScope(AlbumScreen.class)
    public interface Component {
        void inject(AlbumView view);
        void inject(AlbumPresenter presenter);
        void inject(AlbumModel model);
    }

    //endregion
}
