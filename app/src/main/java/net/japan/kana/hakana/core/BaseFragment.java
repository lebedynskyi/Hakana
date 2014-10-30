package net.japan.kana.hakana.core;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public abstract class BaseFragment<T extends BaseActivity> extends Fragment{
    protected T mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void onAttach(Activity activity){
        super.onAttach(activity);
        //Base fragment should be added only for T activity
        mActivity = (T) activity;
    }
}
