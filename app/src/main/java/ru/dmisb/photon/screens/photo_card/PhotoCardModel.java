package ru.dmisb.photon.screens.photo_card;

import io.reactivex.Observable;
import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.flow.ScreenScoper;

public class PhotoCardModel extends BaseModel {

    //region ================= BaseModel =================

    @Override
    protected void initComponent() {
        PhotoCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PHOTO_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    //endregion

    PhotoCardRealm getPhotoCard(String photoCardId) {
        return repository.getPhotoCardFromStorage(photoCardId);
    }

    Observable<UserRealm> getUserFromNetwork(String userId) {
        return repository.getUserFromNetwork(userId);

        /*Observable<UserRealm> networkObs = repository.getUserFromNetwork(userId);
        Observable<UserRealm> realmObs = repository.getUserFromStorage(userId);

        return Observable.mergeDelayError(realmObs, networkObs)
                .distinct(UserRealm::getId);*/
    }

    Observable<UserRealm> getUserFromStorage(String userId) {
        return repository.getUserFromStorage(userId);
    }
}