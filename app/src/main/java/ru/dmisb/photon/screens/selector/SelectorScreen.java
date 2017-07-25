package ru.dmisb.photon.screens.selector;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.di.components.RootComponent;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.screens.selector.filter.FilterScreen;
import ru.dmisb.photon.screens.selector.search.SearchScreen;

@Screen(R.layout.screen_selector)
public class SelectorScreen extends BaseScreen<RootComponent> {

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.SELECTOR_SCOPE_NAME;
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

    @SuppressWarnings("unused")
    @dagger.Module
    public class Module {
        @Provides
        @DaggerScope(SelectorScreen.class)
        SelectorPresenter provideSearchPresenter() {
           return new SelectorPresenter();
        }

        @Provides
        @DaggerScope(SelectorScreen.class)
        SelectorModel provideSearchModel() {
           return new SelectorModel();
        }
    }

    @dagger.Subcomponent(modules = SelectorScreen.Module.class)
    @DaggerScope(SelectorScreen.class)
    public interface Component {
        void inject(SelectorView view);
        void inject(SelectorPresenter presenter);
        void inject(SelectorModel model);

        SearchScreen.Component plus(SearchScreen.Module module);
        FilterScreen.Component plus(FilterScreen.Module module);
    }

    //endregion
}
