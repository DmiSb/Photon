package ru.dmisb.photon.screens.new_card;

import io.reactivex.Observable;
import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.data.network.req.PhotoCardReq;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.flow.ScreenScoper;
import ru.dmisb.photon.job.SaveImageJob;

public class NewCardModel extends BaseModel {

    private UserRealm user;

    Observable<UserRealm> getUser() {
        if (this.user == null) {
            String userId = repository.getSelfUserId();
            Observable<UserRealm> networkObs = repository.getUserFromNetwork(userId);
            Observable<UserRealm> realmObs = repository.getUserFromStorage(userId);

            return Observable.mergeDelayError(realmObs, networkObs)
                    .distinct(UserRealm::getId)
                    .doOnNext(userRealm -> user = userRealm);
        } else {
            return Observable.just(this.user);
        }
    }

    void saveImage(PhotoCardReq photoCard, String photoUri) {
        jobManager.addJobInBackground(new SaveImageJob(photoCard, photoUri));
    }

    //region ================= BaseModel =================

    @Override
    protected void initComponent() {
        NewCardScreen.Component component = ScreenScoper.getComponent(ScreenScoper.NEW_CARD_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    //endregion
}
