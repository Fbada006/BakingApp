package com.disruption.bakingapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.disruption.bakingapp.model.Pastry;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class TinyDb {

    private SharedPreferences preferences;

    public TinyDb(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
    }

    public List<Pastry> getListOfPastries(String key) {
        String jsonText = preferences.getString(key, null);

        Pastry[] pastries = new Gson().fromJson(
                jsonText,
                Pastry[].class);

        return Arrays.asList(pastries);
    }

    public void saveListOfPastries(String key, List<Pastry> pastries) {
        SharedPreferences.Editor editor = preferences.edit();

        String jsonText = new Gson().toJson(pastries);
        editor.putString(key, jsonText);
        editor.apply();
    }

    public static Pastry getPastryFromId(int pastryId, List<Pastry> pastries) {
        if (pastryId == 0) return null;

        Pastry pastry = new Pastry();
        for (Pastry pst : pastries) {
            if (pst.getId() == pastryId) {
                pastry = pst;
            }
        }
        return pastry;
    }

    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    private void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }
}
