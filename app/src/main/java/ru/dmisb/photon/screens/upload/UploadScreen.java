package ru.dmisb.photon.screens.upload;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

@Screen(R.layout.screen_upload)
public class UploadScreen extends BaseScreen<RootComponent> {

    private String albumId;

    public UploadScreen(String albumId) {
        this.albumId = albumId;
    }

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.UPLOAD_SCOPE_NAME;
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
        @DaggerScope(UploadScreen.class)
        UploadPresenter provideUploadPresenter() {
           return new UploadPresenter(albumId);
        }

        @Provides
        @DaggerScope(UploadScreen.class)
        UploadModel provideUploadModel() {
           return new UploadModel();
        }
    }

    @dagger.Subcomponent(modules = UploadScreen.Module.class)
    @DaggerScope(UploadScreen.class)
    public interface Component {
        void inject(UploadView view);
        void inject(UploadPresenter presenter);
        void inject(UploadModel model);
    }

    //endregion
}
