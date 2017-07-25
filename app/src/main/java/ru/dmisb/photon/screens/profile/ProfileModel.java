package ru.dmisb.photon.screens.profile;

import io.reactivex.Observable;
import ru.dmisb.photon.core.BaseModel;
import ru.dmisb.photon.data.network.res.AlbumRes;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.flow.ScreenScoper;

public class ProfileModel extends BaseModel {

    private UserRealm user;

    //region ================= ProfileModel =================

    boolean isSigned() {
        return repository.isSigned();
    }

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

    void addAlbum(AlbumRes albumRes) {
        if (user != null)
            repository.addAlbumToStorage(user, albumRes);
    }

    Observable deleteAlbum(String albumId) {
        if (user != null)
            return repository.deleteAlbumFromApi(user, albumId);
        else
            return Observable.empty();
    }

    void updateAlbum(AlbumRealm albumRealm, AlbumRes albumRes) {
        repository.updateAlbum(albumRealm, albumRes);
    }

    //endregion

    //region ================= BaseModel =================

    @Override
    protected void initComponent() {
        ProfileScreen.Component component = ScreenScoper.getComponent(ScreenScoper.PROFILE_SCOPE_NAME);
        if (component != null)
            component.inject(this);
    }

    //endregion
}
