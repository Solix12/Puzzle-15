package com.salikh.puzzle15.core.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.salikh.puzzle15.R;
import com.salikh.puzzle15.core.models.Userdat;

import java.util.ArrayList;

public class MemoryHelpr {

    private static MemoryHelpr helpr;

    private final SharedPreferences preferences;


    private MemoryHelpr(Context context) {
        //  context
        preferences = context.getSharedPreferences("puzzile15", Context.MODE_PRIVATE);
    }

    public static void init(Context context) {

        if (helpr == null) {
            helpr = new MemoryHelpr(context);
        }

    }

    public static MemoryHelpr getHelpr() {
        return helpr;
    }

    public void saveData(Userdat userData) {

        preferences.edit().putString("name_" + getLastIndex(), userData.getName()).apply();
        preferences.edit().putInt("step_" + getLastIndex(), userData.getStep()).apply();
        preferences.edit().putInt("time_" + getLastIndex(), userData.getTime()).apply();
        saveLastIndex();

    }

    public Userdat getData(int index) {

        Userdat userdat = new Userdat(
                preferences.getString("name_" + index, ""),
                preferences.getInt("step_" + index, 0),
                preferences.getInt("time_" + index, 0)
        );

        return userdat;
    }

    public ArrayList<Userdat> getLastResults() {

        ArrayList<Userdat> list = new ArrayList<>();

        int n = getLastIndex();

        for (int i = 0; i < n; i++) {
            list.add(getData(i));
        }
        return list;
    }

    private void saveLastIndex() {
        preferences.edit().putInt("index", getLastIndex() + 1).apply();
    }

    private int getLastIndex() {
        return preferences.getInt("index", 0);
    }

    public void clearData() {
        preferences.edit().clear().apply();
    }

    public boolean getSoundState() {

        return preferences.getBoolean("sound_state", true);

    }

    public void setSoundState(boolean b) {

        preferences.edit().putBoolean("sound_state", b).apply();

    }

    public int getThemeOrder() {

        return preferences.getInt("theme_order", R.drawable.main_bac);

    }

    public void setThemeOrder(int themeId) {

        preferences.edit().putInt("theme_order", themeId).apply();

    }
}
