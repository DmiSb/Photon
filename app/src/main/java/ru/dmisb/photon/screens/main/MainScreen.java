package ru.dmisb.photon.screens.main;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;

@Screen(R.layout.screen_main)
public class MainScreen extends BaseScreen<RootComponent> {

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.MAIN_SCOPE_NAME;
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
        @DaggerScope(MainScreen.class)
        MainPresenter provideMainPresenter() {
           return new MainPresenter();
        }

        @Provides
        @DaggerScope(MainScreen.class)
        MainModel provideMainModel() {
           return new MainModel();
        }
    }

    @dagger.Subcomponent(modules = MainScreen.Module.class)
    @DaggerScope(MainScreen.class)
    public interface Component {
        void inject(MainView view);
        void inject(MainPresenter presenter);
        void inject(MainModel model);
        void inject(MainAdapter adapter);
    }

    //endregion

}
