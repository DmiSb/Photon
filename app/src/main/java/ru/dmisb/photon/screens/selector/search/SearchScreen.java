package ru.dmisb.photon.screens.selector.search;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.screens.selector.SelectorScreen;

@Screen(R.layout.screen_search)
public class SearchScreen extends BaseScreen<SelectorScreen.Component> {

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.SEARCH_SCOPE_NAME;
    }

    @Override
    public String getParentScopeName() {
        return ScreenScoper.SELECTOR_SCOPE_NAME;
    }

    @Override
    public Object createScreenComponent(SelectorScreen.Component parentComponent) {
        return parentComponent.plus(new Module());
    }

    //endregion

    //region ================= DI =================

    @dagger.Module
    public class Module {
        @Provides
        @DaggerScope(SearchScreen.class)
        SearchPresenter provideSearchPresenter() {
           return new SearchPresenter();
        }

        @Provides
        @DaggerScope(SearchScreen.class)
        SearchModel provideSearchModel() {
           return new SearchModel();
        }
    }

    @dagger.Subcomponent(modules = SearchScreen.Module.class)
    @DaggerScope(SearchScreen.class)
    public interface Component {
        void inject(SearchView view);
        void inject(SearchPresenter presenter);
        void inject(SearchModel model);
    }

    //endregion
}
