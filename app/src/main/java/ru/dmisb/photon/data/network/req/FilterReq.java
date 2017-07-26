package ru.dmisb.photon.data.network.req;

import java.io.Serializable;

public class FilterReq implements Serializable {
    private String dish;
    private String nuances;
    private String decor;
    private String temperature;
    private String light;
    private String lightDirection;
    private String lightSource;

    public void setDish(String dish) {
        this.dish = dish;
    }

    public void setNuances(String nuances) {
        this.nuances = nuances;
    }

    public void setDecor(String decor) {
        this.decor = decor;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public void setLightDirection(String lightDirection) {
        this.lightDirection = lightDirection;
    }

    public void setLightSource(String lightSource) {
        this.lightSource = lightSource;
    }
}
