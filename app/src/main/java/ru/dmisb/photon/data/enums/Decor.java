package ru.dmisb.photon.data.enums;

public enum Decor {
    empty(""),
    simple("simple"),
    holiday("holiday");

    String value;

    Decor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
