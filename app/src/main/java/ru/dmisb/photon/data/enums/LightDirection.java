package ru.dmisb.photon.data.enums;

public enum LightDirection {
    empty(""),
    direct("direct"),
    backlight("backlight"),
    sideLight("sideLight"),
    mixed("mixed");

    String value;

    LightDirection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
