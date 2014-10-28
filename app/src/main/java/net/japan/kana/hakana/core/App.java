package net.japan.kana.hakana.core;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import net.japan.kana.hakana.R;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/28/14
 */
public class App extends Application{
    private AppPreference mPreference;
    private Tracker mTracker;

    @Override
    public void onCreate(){
        super.onCreate();
        initConfig();
    }

    private void initConfig(){
        mPreference = new AppPreference(this);
    }

    synchronized Tracker getTracker() {
        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        mTracker = analytics.newTracker(R.xml.google_analytics);
        mTracker.enableAutoActivityTracking(true);
        mTracker.enableExceptionReporting(true);
        
        return mTracker;
    }
}
