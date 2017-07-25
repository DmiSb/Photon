package ru.dmisb.photon.utils;

public class Constants {
    public static final String LAST_MODIFIED_HEADER = "Last-Modified";
    public static final String IF_MODIFIED_SINCE_HEADER = "If-Modified-Since";
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9_]{1,30}$";
    public static final String NAME_PATTERN = "[a-zA-Zа-яА-Я ]{3,30}$";
    public static final String PASSWORD_PATTERN = "[a-zA-Z0-9]{8,24}$";

    public static final int DESCRIPTION_MIN_LENGTH = 3;
    public static final int DESCRIPTION_MAX_LENGTH = 400;

    public static final int REQUEST_PHOTO_FROM_GALLERY = 500;
}
