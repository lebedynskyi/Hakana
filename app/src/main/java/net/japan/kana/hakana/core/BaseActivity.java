package net.japan.kana.hakana.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import butterknife.ButterKnife;

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

    @Override
    public void setContentView(int layoutResID){
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view){
        super.setContentView(view);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params){
        super.setContentView(view, params);
        ButterKnife.inject(this);
    }
}
