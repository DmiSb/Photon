package ru.dmisb.photon.data.storage.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TagCollectionRealm extends RealmObject {
    @PrimaryKey
    private String tag;

    public TagCollectionRealm() {
    }

    public TagCollectionRealm(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
