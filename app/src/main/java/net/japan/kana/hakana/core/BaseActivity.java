package net.japan.kana.hakana.core;

import android.app.Activity;
import android.os.Bundle;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class BaseActivity extends Activity{
    private AppPreference mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mPreference = new AppPreference(this);
    }

    public AppPreference getPreference(){
        if(mPreference == null){
            mPreference = new AppPreference(this);
        }
        return mPreference;
    }
}
