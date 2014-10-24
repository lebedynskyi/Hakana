package net.japan.kana.hakana.core;

import android.app.Activity;
import android.app.Fragment;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public abstract class BaseFragment<T extends BaseActivity> extends Fragment{
    protected T mActivity;

    @Override
    @SuppressWarnings("unchecked")
    public final void onAttach(Activity activity){
        super.onAttach(activity);
        //Base fragment should be added only for T activity
        mActivity = (T) activity;
    }
}
