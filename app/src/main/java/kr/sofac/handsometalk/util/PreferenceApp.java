package kr.sofac.handsometalk.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.sofac.handsometalk.dto.UserDTO;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.USER_SERVICE;
import static kr.sofac.handsometalk.Constants.GOOGLE_CLOUD_PREFERENCE;
import static kr.sofac.handsometalk.Constants.USER_AUTHORIZATION;
import static kr.sofac.handsometalk.Constants.USER_ID_PREF;
import static kr.sofac.handsometalk.Constants.USER_PREFERENCE;

/**
 * Created by Maxim on 08.11.2017.
 */

public class PreferenceApp {

    private SharedPreferences preferences;
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();

    public PreferenceApp(Context context) {
        preferences = context.getSharedPreferences(USER_SERVICE, MODE_PRIVATE);
    }

    public String getUserID() {
        String userID = preferences.getString(USER_ID_PREF, "1");
       // Timber.e("userID : " + userID.toString());
        return userID;
    }

    public void setUserID(String id) {
        SharedPreferences.Editor editorUser = preferences.edit();
        editorUser.putString(USER_ID_PREF, id);
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

    public String getGoogleKey() {
        //Timber.e("setGoogleKey : %s", preferences.getString(GOOGLE_CLOUD_PREFERENCE, ""));
        return preferences.getString(GOOGLE_CLOUD_PREFERENCE, "");
    }

    public void setGoogleKey(String googleKey) {
        SharedPreferences.Editor editorUser = preferences.edit();
        editorUser.putString(GOOGLE_CLOUD_PREFERENCE, googleKey);
        //Timber.e("setGoogleKey : %s", googleKey);
        editorUser.apply();
        editorUser.commit();
    }

    public UserDTO getUser() {
        return gson.fromJson(preferences.getString(USER_PREFERENCE, ""), new TypeToken<UserDTO>(){}.getType());
    }

    public void setUser(UserDTO userDTO) {
        SharedPreferences.Editor editorUser = preferences.edit();
        editorUser.putString(USER_PREFERENCE, gson.toJson(userDTO));
       // Timber.e("userID : " + gson.toJson(userDTO));
        editorUser.apply();
        editorUser.commit();
        setUserID(userDTO.getId());
    }

}
