package net.japan.kana.hakana.core;

import android.app.Application;

import android.os.Environment;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.db.SQLiteDBHelper;
import net.japan.kana.hakana.tools.Logging;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/28/14
 */
public class App extends Application {
    private AppPreference mPreference;
    private Tracker mTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();

        File newDbFile = new File(getDatabasePath(Const.DB.NAME).getAbsolutePath());
        if(!newDbFile.exists()){
            copyDb(newDbFile);
        }
    }

    private void startHack() {
        SQLiteDBHelper helper = new SQLiteDBHelper(this);
        helper.getWritableDatabase();

        try {
            File dist = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), Const.DB.NAME);
            dist.createNewFile();
            FileUtils.copyFile(getDatabasePath(Const.DB.NAME), dist);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean copyDb(File distination) {
        try {
            InputStream assetDbStream = getAssets().open("db/" + Const.DB.NAME);
            FileUtils.writeByteArrayToFile(distination, IOUtils.toByteArray(assetDbStream));
            IOUtils.closeQuietly(assetDbStream);
            return true;
        }catch (Exception e){
            Logging.e("Cannot copy DB");
            Logging.printStackTrace(e);
        }
        return false;
    }

    private void initConfig() {
        mPreference = new AppPreference(this);
    }

    public synchronized Tracker getTracker() {
        if(mTracker == null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.google_analytics);
            mTracker.enableAutoActivityTracking(true);
            mTracker.enableExceptionReporting(true);
        }
        return mTracker;
    }

    public AppPreference getPreference() {
        return mPreference;
    }
}
