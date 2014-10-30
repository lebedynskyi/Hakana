package net.japan.kana.hakana.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public AppPreference getPreference(){
        App app = (App) getApplication();
        return app.getPreference();
    }
}
