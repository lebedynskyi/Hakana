package net.japan.kana.hakana.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class AppPreference{
    private final SharedPreferences mSharedPrefs;

    public AppPreference(Context context){
        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }
}
