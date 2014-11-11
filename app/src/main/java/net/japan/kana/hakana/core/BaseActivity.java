package net.japan.kana.hakana.core;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class BaseActivity extends Activity {

    private AppPreference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public AppPreference getPreference() {
        if (preference == null) {
            App app = (App) getApplication();
            preference = app.getPreference();
        }

        return preference;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.inject(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.inject(this);
    }

    public void setTitle(String s) {
        getActionBar().setTitle(s);
    }

    public void setTitle(int res) {
        setTitle(getString(res));
    }

    public void setSubTitle(String s) {
        getActionBar().setSubtitle(s);
    }

    public void setSubTitle(int res) {
        setSubTitle(getString(res));
    }
}
