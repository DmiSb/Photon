package ru.dmisb.photon.data.enums;

public enum Temperature {
    empty(""),
    hot("hot"),
    middle("middle"),
    cold("cold");

    String value;

    Temperature(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
