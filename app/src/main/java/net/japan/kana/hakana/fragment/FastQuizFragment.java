package net.japan.kana.hakana.fragment;

import android.os.Bundle;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.activity.MainActivity;
import net.japan.kana.hakana.core.BaseFragment;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/30/14
 */
public class FastQuizFragment extends BaseFragment<MainActivity>{
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        setTitle(getString(R.string.fast_quiz_title));
    }
}
