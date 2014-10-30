package net.japan.kana.hakana.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class AppPreference{
    public static final String IS_USER_KNOW_DRAWER = "is_user_know_drawer";
    private final SharedPreferences mSharedPrefs;

    public AppPreference(Context context){
        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public boolean isUserKnowAboutDrawer(){
        return mSharedPrefs.getBoolean(IS_USER_KNOW_DRAWER, false);
    }

    public void setUserKnowAboutDrawer(boolean value){
        mSharedPrefs.edit().putBoolean(IS_USER_KNOW_DRAWER, value).commit();
    }
}
