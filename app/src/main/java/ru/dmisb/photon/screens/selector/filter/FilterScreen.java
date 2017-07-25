package ru.dmisb.photon.screens.selector.filter;

import dagger.Provides;
import ru.dmisb.photon.R;
import ru.dmisb.photon.core.BaseScreen;
import ru.dmisb.photon.di.DaggerScope;
import ru.dmisb.photon.flow.Screen;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.screens.selector.SelectorScreen;

@Screen(R.layout.screen_filter)
public class FilterScreen extends BaseScreen<SelectorScreen.Component> {

    //region ================= BaseScreen =================

    @Override
    public String getScopeName() {
        return ScreenScoper.FILTER_SCOPE_NAME;
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
        @DaggerScope(FilterScreen.class)
        FilterPresenter provideFilterPresenter() {
           return new FilterPresenter();
        }

        @Provides
        @DaggerScope(FilterScreen.class)
        FilterModel provideFilterModel() {
           return new FilterModel();
        }
    }

    @dagger.Subcomponent(modules = FilterScreen.Module.class)
    @DaggerScope(FilterScreen.class)
    public interface Component {
        void inject(FilterView view);
        void inject(FilterPresenter presenter);
        void inject(FilterModel model);
    }

    //endregion
}
