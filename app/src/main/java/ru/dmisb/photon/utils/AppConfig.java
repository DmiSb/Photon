package ru.dmisb.photon.utils;

import ru.dmisb.photon.BuildConfig;

@SuppressWarnings("unused")
public final class AppConfig {
    private static final boolean DEBUG = BuildConfig.DEBUG;

    // ---------- LOG ----------
    static final boolean LOG_ENABLED = DEBUG;

    // Retrofit config
    public static final String BASE_URL = "http://207.154.248.163:5000/";
    public static final int MAX_CONNECTION_TIMEOUT = 10000;
    public static final int MAX_READ_TIMEOUT = 10000;
    public static final int MAX_WRITE_TIMEOUT = 5000;

    public static final int RETRY_REQUEST_COUNT = 3;
    public static final int RETRY_REQUEST_BASE_DELAY = 1000;

    // JobQueue
    public static final short MIN_CONSUMER_COUNT = 1;
    public static final short MAX_CONSUMER_COUNT = 3;
    public static final short LOAD_FACTOR = 3;
    public static final short CONSUMER_KEEP_ALIVE = 120;
    public static final int INITIAL_BACK_OFF_IN_MS = 1000;

    // Search
    public static final int SEARCH_DELAY = 500;

    // Email
    public static final String SUPPORT_EMAIL = "DmiSb@inbox.ru";
}
