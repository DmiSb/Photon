package ru.dmisb.photon.data.repo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import ru.dmisb.photon.App;
import ru.dmisb.photon.data.dto.AlbumDto;
import ru.dmisb.photon.data.dto.FilterDto;
import ru.dmisb.photon.data.local.PreferencesManager;
import ru.dmisb.photon.data.network.RestCallTransformer;
import ru.dmisb.photon.data.network.RestService;
import ru.dmisb.photon.data.network.req.AlbumReq;
import ru.dmisb.photon.data.network.req.LoginReq;
import ru.dmisb.photon.data.network.req.RegisterReq;
import ru.dmisb.photon.data.network.res.AlbumRes;
import ru.dmisb.photon.data.network.res.ImageRes;
import ru.dmisb.photon.data.storage.StorageManager;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.data.storage.entities.UserRealm;
import ru.dmisb.photon.di.components.DataComponent;
import ru.dmisb.photon.utils.Constants;

@SuppressWarnings("unused")
public class Repository {

    //region ================= Fields =================

    @Inject
    PreferencesManager preferencesManager;
    @Inject
    RestService restService;
    @Inject
    StorageManager storageManager;

    private FilterDto filter;
    private List<String> tagFilter = new ArrayList<>();
    private String searchFilter = "";

    private static Repository instance = null;

    //endregion

    public Repository() {
        initComponent();
    }

    public static Repository getInstance() {
        if (instance == null)
            instance = new Repository();
        return instance;
    }

    //region ================= User from PreferencesManager =================

    private String getToken() {
        return preferencesManager.getToken();
    }

    public boolean isSigned() {
        return !getToken().isEmpty();
    }

    public String getSelfUserId() {
        return preferencesManager.getUserId();
    }

    public void clearSelfUserInfo() {
        preferencesManager.setSelfUserUpdated("");
        preferencesManager.setUserId("");
        preferencesManager.setToken("");
    }

    //endregion

    //region ================= User from Network =================

    public Observable<UserRealm> getUserFromNetwork(String userId) {
        final boolean isSelf = userId.equals(preferencesManager.getUserId());
        final String lastUpdateTime;
        if (isSelf)
            lastUpdateTime = preferencesManager.getSelfUserUpdated();
        else
            lastUpdateTime = preferencesManager.getLastUserUpdated(userId);

        return restService.getUserInfo(userId, lastUpdateTime)
                .subscribeOn(Schedulers.io())
                .doOnNext(listResponse -> {
                    String lastModified = listResponse.headers().get(Constants.LAST_MODIFIED_HEADER);
                    if (lastModified != null) {
                        if (isSelf)
                            preferencesManager.setSelfUserUpdated(lastModified);
                        else
                            preferencesManager.setLastUserUpdated(userId, lastModified);
                    }
                })
                .compose(new RestCallTransformer<>())
                .map(userRes -> storageManager.saveUser(userId, userRes))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> signIn(String email, String password) {
        return restService.signIn(new LoginReq(email, password))
                .subscribeOn(Schedulers.io())
                .doOnNext(listResponse -> {
                    String lastModified = listResponse.headers().get(Constants.LAST_MODIFIED_HEADER);
                    if (lastModified != null)
                        preferencesManager.setSelfUserUpdated(lastModified);
                })
                .compose(new RestCallTransformer<>())
                .flatMap(userRes -> {
                    preferencesManager.setUserId(userRes.getId());
                    preferencesManager.setToken(userRes.getToken());
                    storageManager.saveUser(userRes.getId(), userRes);
                    return Observable.just(true);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Boolean> signUp(RegisterReq registerReq) {
        return restService.signUp(registerReq)
                .subscribeOn(Schedulers.io())
                .doOnNext(listResponse -> {
                    String lastModified = listResponse.headers().get(Constants.LAST_MODIFIED_HEADER);
                    if (lastModified != null)
                        preferencesManager.setSelfUserUpdated(lastModified);
                })
                .compose(new RestCallTransformer<>())
                .flatMap(userRes -> {
                    preferencesManager.setUserId(userRes.getId());
                    preferencesManager.setToken(userRes.getToken());
                    storageManager.saveUser(userRes.getId(), userRes);
                    return Observable.just(true);
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    //endregion

    //region ================= User from Storage =================

    public Observable<UserRealm> getUserFromStorage(String userId) {
        return storageManager.getUser(userId);
    }

    //endregion

    //region ================= Album from Network =================

    public Observable<AlbumRes> addAlbumToApi(AlbumReq album) {
        album.setOwner(getSelfUserId());
        return restService.addAlbum(getSelfUserId(), getToken(), album)
                .subscribeOn(Schedulers.io())
                .compose(new RestCallTransformer<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AlbumRes> updateAlbumFromApi(String albumId, AlbumReq album) {
        album.setOwner(getSelfUserId());
        return restService.editAlbum(getSelfUserId(), albumId, getToken(), album)
                .subscribeOn(Schedulers.io())
                .compose(new RestCallTransformer<>())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable deleteAlbumFromApi(UserRealm user, String albumId){
        return restService.deleteAlbum(getSelfUserId(), albumId, getToken())
                .subscribeOn(Schedulers.io())
                .compose(new RestCallTransformer<>())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> deleteAlbumFromStorage(user, albumId));
    }

    //endregion

    //region ================= Album from Storage =================

    public AlbumRealm addAlbumToStorage(UserRealm user, AlbumRes albumRes) {
        return storageManager.addAlbum(user, albumRes);
    }

    private void deleteAlbumFromStorage(UserRealm user, String albumId){
        storageManager.deleteAlbum(user, albumId);
    }

    public AlbumRealm getAlbumFromStorage(String id) {
        return storageManager.getAlbum(id);
    }

    public void updateAlbum(AlbumDto album) {
        storageManager.updateAlbum(album);
    }

    public void updateAlbum(AlbumRealm albumRealm, AlbumRes albumRes) {
        storageManager.updateAlbum(albumRealm, albumRes);
    }

    //endregion

    //region ================= PhotoCard from Network =================

    public Observable<PhotoCardRealm> getPhotoCardsFromNetwork() {
        return restService.getPhotoCardList(preferencesManager.getLastPhotoCardUpdated())
                .subscribeOn(Schedulers.io())
                .doOnNext(listResponse -> {
                    String lastModified = listResponse.headers().get(Constants.LAST_MODIFIED_HEADER);
                    if (lastModified != null)
                        preferencesManager.setLastPhotoCardUpdated(lastModified);
                })
                .compose(new RestCallTransformer<>())
                .flatMap(Observable::fromIterable)
                .map(photoCardRes -> storageManager.savePhotoCard(photoCardRes))
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ImageRes> uploadImage(MultipartBody.Part body) {
        return restService.uploadImage(getSelfUserId(), getToken(), body)
                .compose(new RestCallTransformer<>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //endregion

    //region ================= PhotoCard from Storage =================

    public Observable<PhotoCardRealm> getPhotoCardsFromStorage() {
        return storageManager.getPhotoCards(filter, tagFilter, searchFilter);
    }

    public PhotoCardRealm getPhotoCardFromStorage(String photoCardId) {
        return storageManager.getPhotoCard(photoCardId);
    }

    //endregion

    //region ================= Filters =================

    public FilterDto getFilter() {
        return filter;
    }

    public void setFilter(FilterDto filter) {
        this.filter = filter;
    }

    public void clearFilter() {
        this.filter = new FilterDto();
    }

    //endregion

    //region ================= Tag Filters =================

    public List<String> getTagFilter() {
        return tagFilter;
    }

    public void addTagToFilter(String tag) {
        if (tagFilter.indexOf(tag) < 0)
            tagFilter.add(tag);
    }

    public void removeTagFromFilter(String tag) {
        int idx = tagFilter.indexOf(tag);
        if (idx >= 0)
            tagFilter.remove(idx);
    }

    public void clearTagFilter() {
        tagFilter.clear();
    }

    //endregion

    //region ================= Search Filter =================

    public void setSearchFilter(String searchFilter) {
        this.searchFilter = searchFilter;
        storageManager.saveSearchHint(searchFilter);
    }

    public String getSearchFilter() {
        return searchFilter;
    }

    public Observable<String> getSearchHints(String searchFilter) {
        return storageManager.getSearchHint(searchFilter);
    }

    //endregion

    //region ================= TagCollection from Network =================

    public Observable<String> getTagCollectionFromNetwork() {
        return restService.getTagCollection(preferencesManager.getLastTagCollectionUpdated())
                .subscribeOn(Schedulers.io())
                .doOnNext(stringResponse -> {
                    String lastModified = stringResponse.headers().get(Constants.LAST_MODIFIED_HEADER);
                    if (lastModified != null)
                        preferencesManager.setLastTagCollectionUpdated(lastModified);

                })
                .compose(new RestCallTransformer<>())
                .flatMap(Observable::fromIterable)
                .observeOn(AndroidSchedulers.mainThread());
    }
    //endregion

    //region ================= TagCollection from Storage =================

    public Observable<String> getTagCollectionFromStorage() {
        return storageManager.getTagCollection();
    }

    //endregion

    //region ================= DI =================

    private void initComponent() {
        DataComponent component = App.getDataComponent();
        if (component != null)
            component.inject(this);
    }

    //endregion

}
