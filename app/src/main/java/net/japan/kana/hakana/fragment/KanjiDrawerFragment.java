package net.japan.kana.hakana.fragment;

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
 * Created by vetalll on 04.11.14.
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

    public void setKanjiSymbol(KanaSymbol clickedSymbol) {
        Toast.makeText(mActivity, clickedSymbol.getHiragana() + " ->> " + clickedSymbol.getAscii(), Toast.LENGTH_SHORT).show();
        this.kanjiSymbol = clickedSymbol;
        kanaDrawer.setKanjiFile("kana/" + clickedSymbol.getAscii());
    }

    public void startDraw() {

    }

    public void stopDraw() {

    }
}
