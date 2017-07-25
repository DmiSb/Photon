package ru.dmisb.photon.data.storage.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import ru.dmisb.photon.data.network.res.FilterRes;

public class FilterRealm extends RealmObject {
    @PrimaryKey
    private String photoCardId;
    private String dish;
    private String nuances;
    private String decor;
    private String temperature;
    private String light;
    private String lightDirection;
    private String lightSource;

    public FilterRealm() {
    }

    public FilterRealm(String photoCardId, FilterRes filterRes) {
        this.photoCardId = photoCardId;
        this.dish = filterRes.getDish();
        this.nuances = filterRes.getNuances();
        this.decor = filterRes.getDecor();
        this.temperature = filterRes.getTemperature();
        this.light = filterRes.getLight();
        this.lightDirection = filterRes.getLightDirection();
        this.lightSource = filterRes.getLightSource();
    }

    //region ================= Getters =================

    public String getPhotoCardId() {
        return photoCardId;
    }

    public String getDish() {
        return dish;
    }

    public String getNuances() {
        return nuances;
    }

    public String getDecor() {
        return decor;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getLight() {
        return light;
    }

    public String getLightDirection() {
        return lightDirection;
    }

    public String getLightSource() {
        return lightSource;
    }

    //endregion
}