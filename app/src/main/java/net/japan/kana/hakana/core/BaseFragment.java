package net.japan.kana.hakana.core;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import butterknife.ButterKnife;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public abstract class BaseFragment<T extends BaseActivity> extends Fragment {
    protected T mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Tracker mTracker = ((App) getActivity().getApplication()).getTracker();
            mTracker.setScreenName(getClass().getName());
            mTracker.send(new HitBuilders.AppViewBuilder().build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        //Base fragment should be added only for T activity
        mActivity = (T) activity;
    }

    protected void setTitle(String s) {
        mActivity.setTitle(s);
    }

    protected void setTitle(int res) {
        mActivity.setTitle(res);
    }

    protected void setSubTitle(String s) {
        mActivity.setSubTitle(s);
    }

    protected void setSubTitle(int res) {
        mActivity.setSubTitle(res);
    }
}
