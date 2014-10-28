package net.japan.kana.hakana.core;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class BaseActivity extends Activity{
    protected Tracker mTracker;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTracker = ((App)getApplication()).getTracker();
        mTracker.setScreenName(getClass().getName());
        mTracker.send(new HitBuilders.AppViewBuilder().build());
    }
}
