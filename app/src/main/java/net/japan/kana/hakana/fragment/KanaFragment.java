package net.japan.kana.hakana.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.activity.MainActivity;
import net.japan.kana.hakana.adapter.KanaSymbolAdapter;
import net.japan.kana.hakana.core.BaseFragment;
import net.japan.kana.hakana.core.Const;
import net.japan.kana.hakana.models.KanaSymbol;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.InjectView;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/30/14
 */
public class KanaFragment extends BaseFragment<MainActivity> {
    enum KanaSet {
        SEION, DAKUON, YOUON
    }

    enum KanaType {
        HIRAGANA, KATAKANA
    }

    private final ArrayList<KanaSymbol> currentKanasSymbols = new ArrayList<KanaSymbol>();

    private KanaSet setOfKana = KanaSet.SEION;
    private KanaType kanatype = KanaType.HIRAGANA;
    private KanaSymbolAdapter kanaAdapter;
    private KanaSymbolAdapter.KanaType currentType = KanaSymbolAdapter.KanaType.HIRAGANA;
    private KanjiDrawerFragment drawerFragment;

    @InjectView(R.id.kana_grid_view)
    GridView kanaGrid;
    @InjectView(R.id.kanji_drawer_container)
    View mKanjiDrawerContainer;
    @InjectView(R.id.kanji_drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kanaAdapter = new KanaSymbolAdapter(currentType, currentKanasSymbols);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_kana_items, menu);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kana, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        kanaGrid.setAdapter(kanaAdapter);
        kanaGrid.setOnItemClickListener(symbolClickListener);
        setTitle(R.string.hiragana);
        refreshState();
    }

    public void refreshState() {
        //TODO implement
        currentKanasSymbols.clear();
        currentKanasSymbols.addAll(Arrays.asList(Const.Kana.getSeionKana()));
    }

    private AdapterView.OnItemClickListener symbolClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            KanaSymbol clickedSymbol = kanaAdapter.getItem(position);
            if (drawerFragment == null) {
                drawerFragment = (KanjiDrawerFragment) getFragmentManager().findFragmentById(R.id.kanji_drawer_container);
            }

            if (!mActivity.getPreference().isUserKnowAboutKanjiDrawer()) {
                mActivity.getPreference().setUserKnowAboutKanjiDrawer(true);
                if (!mDrawerLayout.isDrawerOpen(mKanjiDrawerContainer)) {
                    mDrawerLayout.openDrawer(mKanjiDrawerContainer);
                }
            }
            drawerFragment.setKanjiSymbol(clickedSymbol, true);
        }
    };
}
