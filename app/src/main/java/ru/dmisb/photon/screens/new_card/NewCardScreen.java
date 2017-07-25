package ru.dmisb.photon.screens.new_card;

import android.net.Uri;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

@Screen(R.layout.screen_new_card)
public class NewCardScreen extends BaseScreen<RootComponent> {

    private String albumId;
    private Uri photoUri;

    public NewCardScreen(String albumId, Uri photoUri) {
        this.albumId = albumId;
        this.photoUri = photoUri;
    }

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.NEW_CARD_SCOPE_NAME;
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
        @DaggerScope(NewCardScreen.class)
        NewCardPresenter provideNewCardPresenter() {
           return new NewCardPresenter(albumId, photoUri);
        }

        @Provides
        @DaggerScope(NewCardScreen.class)
        NewCardModel provideNewCardModel() {
           return new NewCardModel();
        }
    }

    @dagger.Subcomponent(modules = NewCardScreen.Module.class)
    @DaggerScope(NewCardScreen.class)
    public interface Component {
        void inject(NewCardView view);
        void inject(NewCardPresenter presenter);
        void inject(NewCardModel model);
    }

    //endregion


}
