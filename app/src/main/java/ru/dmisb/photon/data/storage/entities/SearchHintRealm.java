package ru.dmisb.photon.data.storage.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SearchHintRealm extends RealmObject {
    @PrimaryKey
    private String searchHint;

    public SearchHintRealm() {
    }

    public SearchHintRealm(String searchHint) {
        this.searchHint = searchHint;
    }

    public String getSearchHint() {
        return searchHint;
    }

    public void setSearchHint(String searchHint) {
        this.searchHint = searchHint;
    }
}
