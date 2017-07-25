package ru.dmisb.photon.data.storage;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import ru.dmisb.photon.data.dto.AlbumDto;
import ru.dmisb.photon.data.dto.FilterDto;
import ru.dmisb.photon.data.network.res.AlbumRes;
import ru.dmisb.photon.data.network.res.PhotoCardRes;
import ru.dmisb.photon.data.network.res.UserRes;
import ru.dmisb.photon.data.storage.entities.AlbumRealm;
import ru.dmisb.photon.data.storage.entities.FilterRealm;
import ru.dmisb.photon.data.storage.entities.PhotoCardRealm;
import ru.dmisb.photon.data.storage.entities.SearchHintRealm;
import ru.dmisb.photon.data.storage.entities.TagCollectionRealm;
import ru.dmisb.photon.data.storage.entities.TagRealm;
import ru.dmisb.photon.data.storage.entities.UserRealm;

@SuppressWarnings("unused")
public class StorageManager {

    private Realm realmInstance;

    //region ================= User =================

    public Observable<UserRealm> getUser(String userId) {
        UserRealm result = getQueryRealmInstance()
                .where(UserRealm.class)
                .equalTo("id", userId)
                .findFirstAsync();

        if (result != null)
            return Observable.just(result)
                .filter(userRealm -> userRealm.isLoaded())
                .subscribeOn(AndroidSchedulers.mainThread());
        else
            return Observable.empty();
    }

    public UserRealm saveUser(String userId, UserRes userRes) {
        Realm realm = Realm.getDefaultInstance();
        if (userRes.getId() == null || userRes.getId().isEmpty())
            userRes.setId(userId);
        UserRealm userRealm = new UserRealm(userRes);

        for (AlbumRes albumRes : userRes.getAlbums()) {
            AlbumRealm albumRealm = new AlbumRealm(albumRes);
            userRealm.getAlbums().add(albumRealm);

            for (PhotoCardRes photoCardRes : albumRes.getPhotocards()) {
                PhotoCardRealm photoCardRealm = new PhotoCardRealm(photoCardRes);
                albumRealm.getPhotocards().add(photoCardRealm);

                if (photoCardRes.getFilter() != null)
                    photoCardRealm.setFilter(new FilterRealm(photoCardRes.getId(), photoCardRes.getFilter()));

                for (String tag : photoCardRes.getTags()) {
                    photoCardRealm.getTags().add(new TagRealm(tag));
                }
            }
        }

        realm.executeTransaction(realm1 -> {
            UserRealm updateUser = realm1.where(UserRealm.class)
                    .equalTo("id", userRes.getId())
                    .findFirst();

            if (updateUser != null) {
                updateUser.getAlbums().deleteAllFromRealm();
            }

            realm1.insertOrUpdate(userRealm);
        });
        return userRealm;
    }

    //endregion

    //region ================= Album =================

    public AlbumRealm addAlbum(UserRealm user, AlbumRes albumRes) {
        Realm realm = Realm.getDefaultInstance();
        AlbumRealm albumRealm = new AlbumRealm(albumRes);
        realm.executeTransaction(realm1 -> {
            user.getAlbums().add(albumRealm);
            user.setAlbumCount(user.getAlbumCount() + 1);
        });
        realm.close();
        return albumRealm;
    }

    public void deleteAlbum(UserRealm user, String albumId) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            AlbumRealm album = user.getAlbums().where().equalTo("id", albumId).findFirst();
            if (album != null) {
                album.setActive(false);
            }
            user.setAlbumCount(user.getAlbumCount() - 1);
        });
        realm.close();
    }

    public AlbumRealm getAlbum(String id) {
        return getQueryRealmInstance().where(AlbumRealm.class)
                .equalTo("id", id)
                .findFirst();
    }

    public void updateAlbum(AlbumDto album) {
        Realm realm = Realm.getDefaultInstance();
        AlbumRealm albumRealm = realm.where(AlbumRealm.class).equalTo("id", album.getId()).findFirst();
        realm.executeTransaction(realm1 -> {
            albumRealm.setTitle(album.getTitle());
            albumRealm.setDescription(album.getDescription());
        });
        realm.close();
    }

    public void updateAlbum(AlbumRealm album, AlbumRes albumRes) {
        getQueryRealmInstance().executeTransaction(realm -> {
            album.setTitle(albumRes.getTitle());
            album.setDescription(albumRes.getDescription());
        });
    }

    //endregion

    //region ================= PhotoCard =================

    public Observable<PhotoCardRealm> getPhotoCards(FilterDto filter, List<String> tagFilter, String textFilter) {
        RealmQuery<PhotoCardRealm> query = getQueryRealmInstance()
                .where(PhotoCardRealm.class);

        if (filter != null) {
            if (filter.isMeat())
                query.equalTo("filter.dish", "meat");
            if (filter.isFish())
                query.equalTo("filter.dish", "fish");
            if (filter.isVegetables())
                query.equalTo("filter.dish", "vegetables");
            if (filter.isFruit())
                query.equalTo("filter.dish", "fruit");
            if (filter.isCheese())
                query.equalTo("filter.dish", "cheese");
            if (filter.isDesert())
                query.equalTo("filter.dish", "dessert");
            if (filter.isDrinks())
                query.equalTo("filter.dish", "drinks");

            if (filter.isNuancesRed())
                query.equalTo("filter.nuances", "red");
            if (filter.isNuancesOrange())
                query.equalTo("filter.nuances", "orange");
            if (filter.isNuancesYellow())
                query.equalTo("filter.nuances", "yellow");
            if (filter.isNuancesGreen())
                query.equalTo("filter.nuances", "green");
            if (filter.isNuancesLightBlue())
                query.equalTo("filter.nuances", "lightBlue");
            if (filter.isNuancesBlue())
                query.equalTo("filter.nuances", "blue");
            if (filter.isNuancesViolet())
                query.equalTo("filter.nuances", "violet");
            if (filter.isNuancesBrown())
                query.equalTo("filter.nuances", "brown");
            if (filter.isNuancesBlack())
                query.equalTo("filter.nuances", "black");
            if (filter.isNuancesWhite())
                query.equalTo("filter.nuances", "white");
        }

        if (tagFilter.size() > 0) {
            for (String tag :  tagFilter) {
                query.equalTo("tags.tag", tag);
            }
        }

        if (!textFilter.isEmpty())
            query.contains("title", textFilter, Case.INSENSITIVE);

        RealmResults<PhotoCardRealm> result = query.findAllAsync();

        if (result != null)
            return Observable.fromIterable(result)
                    .filter(photoCardRealm -> photoCardRealm.isLoaded())
                    .subscribeOn(AndroidSchedulers.mainThread());
        else
            return Observable.empty();
    }

    public PhotoCardRealm getPhotoCard(String photoCardId) {
        return getQueryRealmInstance()
                .where(PhotoCardRealm.class)
                .equalTo("id", photoCardId)
                .findFirst();
    }

    public PhotoCardRealm savePhotoCard(PhotoCardRes photoCardRes) {

        Realm realm = Realm.getDefaultInstance();
        PhotoCardRealm photoCardRealm = new PhotoCardRealm(photoCardRes);

        if (photoCardRes.getFilter() != null)
            photoCardRealm.setFilter(
                    new FilterRealm(photoCardRes.getId(), photoCardRes.getFilter())
            );

        for (String tag : photoCardRes.getTags()) {
            photoCardRealm.getTags().add(new TagRealm(tag));
        }

        realm.executeTransaction(realm1 -> {
            PhotoCardRealm updatedPhotoCardRealm = realm1.where(PhotoCardRealm.class)
                    .equalTo("id", photoCardRes.getId())
                    .findFirst();
            if (updatedPhotoCardRealm != null)
                updatedPhotoCardRealm.getTags().deleteAllFromRealm();

            realm1.insertOrUpdate(photoCardRealm);
        });
        realm.close();
        return photoCardRealm;
    }

    //endregion

    //region ================= TagCollection =================

    public void saveTag(String tag) {
        Realm realm = Realm.getDefaultInstance();
        TagCollectionRealm tagRealm = new TagCollectionRealm(tag);
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(tagRealm));
        realm.close();
    }

    public Observable<String> getTagCollection() {
        RealmResults<TagCollectionRealm> result = getQueryRealmInstance()
                .where(TagCollectionRealm.class)
                .findAllSorted("tag");

        if (result != null)
            return Observable.fromIterable(result)
                    .filter(tagCollectionRealm -> tagCollectionRealm.isLoaded())
                    .map(TagCollectionRealm::getTag)
                    .subscribeOn(AndroidSchedulers.mainThread());
        else
            return Observable.empty();
    }

    //endregion

    //region ================= SearchHint =================

    public void saveSearchHint(String searchHint) {
        Realm realm = Realm.getDefaultInstance();
        SearchHintRealm searchHintRealm = new SearchHintRealm(searchHint);
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(searchHintRealm));
        realm.close();
    }

    public Observable<String> getSearchHint(String searchHint) {
        RealmResults<SearchHintRealm> result = getQueryRealmInstance()
                .where(SearchHintRealm.class)
                .contains("searchHint", searchHint, Case.INSENSITIVE)
                .findAllSorted("searchHint");

        if (result != null)
            return Observable.fromIterable(result)
                    .filter(tagCollectionRealm -> tagCollectionRealm.isLoaded())
                    .map(SearchHintRealm::getSearchHint)
                    .subscribeOn(AndroidSchedulers.mainThread());
        else
            return Observable.empty();
    }

    //endregion

    private Realm getQueryRealmInstance() {
        if (realmInstance == null || realmInstance.isClosed()) {
            realmInstance = Realm.getDefaultInstance();
        }
        return realmInstance;
    }
}
