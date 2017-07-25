package ru.dmisb.photon.screens.selector.search;

import java.util.List;

import io.reactivex.Observable;
import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.flow.ScreenScoper;

public class SearchModel extends BaseModel {

    @Override
    protected void initComponent() {
        SearchScreen.Component component = ScreenScoper.getComponent(ScreenScoper.SEARCH_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    Observable<String> getTagCollection() {
        Observable<String> networkObs = repository.getTagCollectionFromNetwork();
        Observable<String> realmObs = repository.getTagCollectionFromStorage();

        return Observable.mergeDelayError(realmObs, networkObs)
                .distinct(s -> s)
                .sorted();
    }

    List<String> getTagFilter() {
        return repository.getTagFilter();
    }

    void addTagToFilter(String tag) {
        repository.addTagToFilter(tag);
    }

    void removeTagFromFilter(String tag) {
        repository.removeTagFromFilter(tag);
    }

    Observable<String> getSearchHints(String searchHint) {
        return repository.getSearchHints(searchHint);
    }

    String getSearchHint() {
        return repository.getSearchFilter();
    }

    void setSearchFilter(String searchFilter) {
        repository.setSearchFilter(searchFilter);
    }
}
