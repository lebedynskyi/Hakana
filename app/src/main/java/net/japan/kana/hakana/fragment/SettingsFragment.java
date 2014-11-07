package net.japan.kana.hakana.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import net.japan.kana.hakana.R;
import net.japan.kana.hakana.core.BaseActivity;


/**
 * Author Vitalii Lebedynskyi
 * Date 10/30/14
 */
public class SettingsFragment extends PreferenceFragment{
    private BaseActivity activity;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefrence_screen);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (BaseActivity) activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity.setTitle("Settings");
    }
}
