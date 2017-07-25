package ru.dmisb.photon.utils;

import ru.dmisb.photon.BuildConfig;

public final class AppConfig {
    private static final boolean DEBUG = BuildConfig.DEBUG;

    // ---------- LOG ----------
    public static final boolean LOG_ENABLED = DEBUG;

    // Retrofit config
    public static final String BASE_URL = "http://207.154.248.163:5000/";
    public static final int MAX_CONNECTION_TIMEOUT = 10000;
    public static final int MAX_READ_TIMEOUT = 10000;
    public static final int MAX_WRITE_TIMEOUT = 5000;

    public static final int RETRY_REQUEST_COUNT = 3;
    public static final int RETRY_REQUEST_BASE_DELAY = 1000;

    // Search
    public static final int SEARCH_DELAY = 500;

    // Email
    public static final String SUPPORT_EMAIL = "DmiSb@inbox.ru";
}
