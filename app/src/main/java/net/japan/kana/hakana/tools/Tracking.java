package net.japan.kana.hakana.tools;

import android.content.Context;

import com.google.android.gms.analytics.Tracker;

import net.japan.kana.hakana.core.App;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/28/14
 */
public class Tracking{
    public static void trackException(String tag, String msg, Context c){
        App app = (App)c.getApplicationContext();
        Tracker t = app.getTracker();
        //TODO send exception;
    }
}
