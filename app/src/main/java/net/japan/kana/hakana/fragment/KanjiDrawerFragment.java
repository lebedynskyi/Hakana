package net.japan.kana.hakana.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.InjectView;
import net.japan.kana.hakana.R;
import net.japan.kana.hakana.activity.MainActivity;
import net.japan.kana.hakana.core.BaseFragment;
import net.japan.kana.hakana.db.tables.Kana;
import net.japan.kana.hakana.widgets.KanjiDrawer;

/**
 * Author Vitalii Lebedynskyi
 * Date 11/4/14
 */
public class KanjiDrawerFragment extends BaseFragment<MainActivity> {
    @InjectView(R.id.kana_drawer)
    KanjiDrawer kanaDrawer;
    private String idOfAsett;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kanji_drawer, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        kanaDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                setKanjiSymbol(idOfAsett, false);
            }
        }, 150);
    }

    public void setKanjiSymbol(String idOfAsett, boolean playSound) {
        //TODO play sound and check ty
        this.idOfAsett = idOfAsett;
        kanaDrawer.setKanjiFile("kana/" + idOfAsett);
    }

    public void startDraw() {

    }

    public void stopDraw() {

    }
}
