package ru.dmisb.photon.screens.main;

import io.reactivex.Observable;
import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.flow.ScreenScoper;

public class MainModel extends BaseModel {

    Observable<PhotoCardRealm> getPhotoCards() {
        Observable<PhotoCardRealm> networkObs = repository.getPhotoCardsFromNetwork();
        Observable<PhotoCardRealm> realmObs = repository.getPhotoCardsFromStorage();

        return Observable.mergeDelayError(realmObs, networkObs)
                .distinct(PhotoCardRealm::getId);
    }

    boolean isSigned() {
        return repository.isSigned();
    }

    void clearUserInfo() {
        repository.clearSelfUserInfo();
    }

    boolean isFilterActive() {
        return (repository.getFilter() != null && repository.getFilter().isActive()) ||
                repository.getTagFilter().size() > 0 ||
                !repository.getSearchFilter().isEmpty();
    }

    void clearFilter() {
        repository.clearFilter();
        repository.clearTagFilter();
        repository.setSearchFilter("");
    }

    //region ================= BaseModel =================

    @Override
    protected void initComponent() {
        MainScreen.Component component = ScreenScoper.getComponent(ScreenScoper.MAIN_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    //endregion
}