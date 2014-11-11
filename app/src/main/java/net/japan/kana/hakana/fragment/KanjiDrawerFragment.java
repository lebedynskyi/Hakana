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
import net.japan.kana.hakana.models.KanaSymbol;
import net.japan.kana.hakana.widgets.KanjiDrawer;

/**
 * Author Vitalii Lebedynskyi
 * Date 11/4/14
 */
public class KanjiDrawerFragment extends BaseFragment<MainActivity> {
    @InjectView(R.id.kana_drawer)
    KanjiDrawer kanaDrawer;

    private KanaSymbol kanjiSymbol;

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
        //There is a bug during rotation. handwritten symbol is disappearing.
        if(kanjiSymbol == null){
            return;
        }

        kanaDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                setKanjiSymbol(kanjiSymbol, false);
            }
        }, 150);
    }

    public void setKanjiSymbol(KanaSymbol clickedSymbol, boolean playSound) {
        //TODO play sound
        Toast.makeText(mActivity, clickedSymbol.getHiragana() + " ->> " + clickedSymbol.getHiraganaAscii(), Toast.LENGTH_SHORT).show();
        this.kanjiSymbol = clickedSymbol;
        kanaDrawer.setKanjiFile("kana/" + clickedSymbol.getHiraganaAscii());
    }

    public void startDraw() {

    }

    public void stopDraw() {

    }
}
