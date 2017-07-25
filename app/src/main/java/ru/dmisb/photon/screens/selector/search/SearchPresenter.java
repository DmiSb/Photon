package ru.dmisb.photon.screens.selector.search;

import java.util.List;

import ru.dmisb.photon.core.BasePresenter;
import ru.dmisb.photon.flow.ScreenScoper;

public class SearchPresenter
        extends BasePresenter<SearchView, SearchModel>
        implements ISearchPresenter{

    //region ================= ISearchPresenter =================

    @Override
    public void addTagToFilter(String tag) {
        model.addTagToFilter(tag);
    }

    @Override
    public void removeTagFromFilter(String tag) {
        model.removeTagFromFilter(tag);
    }

    @Override
    public void onChangeSearch(String searchText) {
        if (getView() != null)
            getView().clearSearchHints();
            model.getSearchHints(searchText)
                    .subscribe(
                        s -> getView().addSearchHint(s)
                    );
    }

    @Override
    public void applySearch(String searchText) {
        model.setSearchFilter(searchText);
        if (!searchText.isEmpty()) {
            rootPresenter.showMainScreen();
        }
    }

    //endregion

    //region ================= BasePresenter =================

    @Override
    protected void initComponent() {
        SearchScreen.Component component = ScreenScoper.getComponent(ScreenScoper.SEARCH_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    @Override
    protected void initView() {
        if (getView() != null) {
            List<String> tagFilter = model.getTagFilter();

            model.getTagCollection()
                    .subscribe(
                        s -> getView().addTag(s, tagFilter.indexOf(s) >= 0)
                    );

            getView().setSearchText(model.getSearchHint());
        }
    }

    @Override
    protected void initActionBar() {
        // empty
    }

    //endregion
}
