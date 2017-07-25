package ru.dmisb.photon.screens.photo_card;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

@Screen(R.layout.screen_photo_card)
public class PhotoCardScreen extends BaseScreen<RootComponent> {

    private String photoCardId;

    public PhotoCardScreen(String photoCardId) {
        this.photoCardId = photoCardId;
    }

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.PHOTO_CARD_SCOPE_NAME;
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
        @DaggerScope(PhotoCardScreen.class)
        PhotoCardPresenter providePhotoCardPresenter() {
           return new PhotoCardPresenter(photoCardId);
        }

        @Provides
        @DaggerScope(PhotoCardScreen.class)
        PhotoCardModel providePhotoCardModel() {
           return new PhotoCardModel();
        }
    }

    @dagger.Subcomponent(modules = Module.class)
    @DaggerScope(PhotoCardScreen.class)
    public interface Component {
        void inject(PhotoCardView view);
        void inject(PhotoCardPresenter presenter);
        void inject(PhotoCardModel model);
    }

    //endregion
}
