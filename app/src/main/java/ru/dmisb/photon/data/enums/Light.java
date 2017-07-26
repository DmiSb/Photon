package ru.dmisb.photon.data.enums;

public enum Light {
    empty(""),
    natural("natural"),
    synthetic("synthetic"),
    mixed("mixed");

    String value;

    Light(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
