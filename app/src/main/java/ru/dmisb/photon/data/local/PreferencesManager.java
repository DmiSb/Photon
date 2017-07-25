package ru.dmisb.photon.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesManager {

    private static final String USER_ID_KEY = "USER_ID";
    private static final String USER_TOKEN_KEY = "USER_TOKEN";
    private static final String LAST_PHOTO_CARDS_UPDATED_KEY = "LAST_PHOTO_CARDS_UPDATED";
    private static final String LAST_USER_UPDATED_KEY = "LAST_USER_UPDATED_";
    private static final String LAST_SELF_UPDATED_KEY = "LAST_SELF_UPDATED";
    private static final String LAST_TAG_COLLECTION_UPDATED_KEY = "LAST_TAG_COLLECTION_UPDATED";

    private final SharedPreferences sharedPreferences;

    public PreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public String getToken() {
        return sharedPreferences.getString(USER_TOKEN_KEY, "");
    }

    public void setToken(String token) {
        setKeyValue(USER_TOKEN_KEY, token);
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID_KEY, "");
    }

    public void setUserId(String userId) {
        setKeyValue(USER_ID_KEY, userId);
    }

    public String getLastPhotoCardUpdated() {
        return sharedPreferences.getString(LAST_PHOTO_CARDS_UPDATED_KEY, "Thu, 01 Jan 1970 00:00:00 GMT");
    }

    public void setLastPhotoCardUpdated(String lastPhotoCardUpdated) {
        setKeyValue(LAST_PHOTO_CARDS_UPDATED_KEY, lastPhotoCardUpdated);
    }

    public String getLastUserUpdated(String userId) {
        return sharedPreferences.getString(LAST_USER_UPDATED_KEY + userId, "Thu, 01 Jan 1970 00:00:00 GMT");
    }

    public void setLastUserUpdated(String userId, String lastUserUpdated) {
        setKeyValue(LAST_USER_UPDATED_KEY + userId, lastUserUpdated);
    }

    public String getSelfUserUpdated() {
        return sharedPreferences.getString(LAST_SELF_UPDATED_KEY, "Thu, 01 Jan 1970 00:00:00 GMT");
    }

    public void setSelfUserUpdated(String lastUserUpdated) {
        setKeyValue(LAST_SELF_UPDATED_KEY, lastUserUpdated);
    }

    public String getLastTagCollectionUpdated() {
        return sharedPreferences.getString(LAST_TAG_COLLECTION_UPDATED_KEY, "Thu, 01 Jan 1970 00:00:00 GMT");
    }

    public void setLastTagCollectionUpdated(String lastTagCollectionUpdated) {
        setKeyValue(LAST_TAG_COLLECTION_UPDATED_KEY, lastTagCollectionUpdated);
    }

    //----------------------------------------------------------------------------------------------

    private void setKeyValue(String keyName, String keyValue) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(keyName, keyValue);
        editor.apply();
    }
}
