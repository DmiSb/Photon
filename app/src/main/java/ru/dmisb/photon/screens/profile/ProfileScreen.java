package ru.dmisb.photon.screens.profile;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

@Screen(R.layout.screen_profile)
public class ProfileScreen extends BaseScreen<RootComponent> {

    private int position;

    public ProfileScreen(int position) {
        this.position = position;
    }

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.PROFILE_SCOPE_NAME;
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
        @DaggerScope(ProfileScreen.class)
        ProfilePresenter provideProfilePresenter() {
           return new ProfilePresenter(position);
        }

        @Provides
        @DaggerScope(ProfileScreen.class)
        ProfileModel provideProfileModel() {
           return new ProfileModel();
        }
    }

    @dagger.Subcomponent(modules = ProfileScreen.Module.class)
    @DaggerScope(ProfileScreen.class)
    public interface Component {
        void inject(ProfileView view);
        void inject(ProfilePresenter presenter);
        void inject(ProfileModel model);
        void inject(ProfileAdapter adapter);
    }

    //endregion
}
