package ru.dmisb.photon.data.storage.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TagRealm extends RealmObject {
    @PrimaryKey
    private String tag;

    public TagRealm() {
    }

    public TagRealm(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
