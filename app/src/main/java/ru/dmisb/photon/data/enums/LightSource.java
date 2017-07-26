package ru.dmisb.photon.data.enums;

public enum LightSource {
    empty(""),
    one("one"),
    two("one"),
    three("one");

    String value;

    LightSource(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
