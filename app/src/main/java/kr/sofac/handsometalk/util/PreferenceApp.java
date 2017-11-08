package kr.sofac.handsometalk.util;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.USER_SERVICE;
import static kr.sofac.handsometalk.Constants.USER_AUTHORIZATION;
import static kr.sofac.handsometalk.Constants.USER_ID_PREF;

/**
 * Created by Maxim on 08.11.2017.
 */

public class PreferenceApp {

    private SharedPreferences preferences;

    public PreferenceApp(Context context) {
        preferences = context.getSharedPreferences(USER_SERVICE, MODE_PRIVATE);
    }

    public Long getUserID() {
        return preferences.getLong(USER_ID_PREF, 1L);
    }

    public void setUserID(Long id) {
        SharedPreferences.Editor editorUser = preferences.edit();
        editorUser.putLong(USER_ID_PREF, id);
        editorUser.apply();
        editorUser.commit();
    }

    public Boolean getAuthorization() {
        return preferences.getBoolean(USER_AUTHORIZATION, false);
    }

    public void setAuthorization(Boolean isAuthorization) {
        SharedPreferences.Editor editorUser = preferences.edit();
        editorUser.putBoolean(USER_AUTHORIZATION, isAuthorization);
        editorUser.apply();
        editorUser.commit();
    }

}
