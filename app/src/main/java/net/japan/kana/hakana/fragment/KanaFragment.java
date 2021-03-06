package net.japan.kana.hakana.fragment;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.widget.AdapterView;
import android.widget.GridView;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.activity.MainActivity;
import net.japan.kana.hakana.adapter.KanaCursorAdapter;
import net.japan.kana.hakana.core.BaseFragment;
import net.japan.kana.hakana.db.LocalDataProvider;
import net.japan.kana.hakana.db.tables.Kana;

import butterknife.InjectView;
import net.japan.kana.hakana.tools.Logging;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/30/14
 */
public class KanaFragment extends BaseFragment<MainActivity> {
    public static enum KanaType {
        HIRAGANA, KATAKANA
    }

    private Kana.Set kanaSet = Kana.Set.SEION;
    private KanaType kanaType = KanaType.HIRAGANA;

    private KanaCursorAdapter kanaAdapter;
    private KanjiDrawerFragment drawerFragment;
    public Kana clickedSymbol;

    @InjectView(R.id.kana_grid_view)
    GridView kanaGrid;
    @InjectView(R.id.kanji_drawer_container)
    View mKanjiDrawerContainer;
    @InjectView(R.id.kanji_drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kanaAdapter = new KanaCursorAdapter(getActivity());
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_kana_items, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.diacritics_seion){
            if(kanaSet == Kana.Set.SEION){
                return true;
            }else {
                kanaSet = Kana.Set.SEION;
                getLoaderManager().restartLoader(100, null, kanaLoaderCallback);
            }
        }else if(item.getItemId() == R.id.diacritics_dakuon){
            if(kanaSet == Kana.Set.DAKYOUN){
                return true;
            }else {
                kanaSet = Kana.Set.DAKYOUN;
                getLoaderManager().restartLoader(100, null, kanaLoaderCallback);
            }
        }else if(item.getItemId() == R.id.diacritics_youon){
            if(kanaSet == Kana.Set.YOUON){
                return true;
            }else {
                kanaSet = Kana.Set.YOUON;
                getLoaderManager().restartLoader(100, null, kanaLoaderCallback);
            }
        }else if(item.getItemId() == R.id.kana_scripts){
            kanaType = kanaType == KanaType.HIRAGANA ? KanaType.KATAKANA : KanaType.HIRAGANA;
            kanaAdapter.setkanaType(kanaType);
            kanaAdapter.notifyDataSetChanged();

            if(clickedSymbol != null){
                drawerFragment.setKanjiSymbol(kanaType == KanaType.HIRAGANA? clickedSymbol.getHiraganaAscii() : clickedSymbol.getKatakanaAscii(), true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kana, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        kanaGrid.setAdapter(kanaAdapter);
        kanaGrid.setOnItemClickListener(symbolClickListener);
        getLoaderManager().restartLoader(100, null, kanaLoaderCallback);
    }

    @Override
    public boolean consumeBackEvent() {
        if(mDrawerLayout.isDrawerOpen(mKanjiDrawerContainer)){
            mDrawerLayout.closeDrawer(mKanjiDrawerContainer);
            return true;
        }
        return super.consumeBackEvent();
    }

    private AdapterView.OnItemClickListener symbolClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            clickedSymbol = kanaAdapter.getItem(position);
            if (drawerFragment == null) {
                drawerFragment = (KanjiDrawerFragment) getFragmentManager().findFragmentById(R.id.kanji_drawer_container);
            }

            if (!mActivity.getPreference().isUserKnowAboutKanjiDrawer()) {
                mActivity.getPreference().setUserKnowAboutKanjiDrawer(true);
                if (!mDrawerLayout.isDrawerOpen(mKanjiDrawerContainer)) {
                    mDrawerLayout.openDrawer(mKanjiDrawerContainer);
                }
            }
            drawerFragment.setKanjiSymbol(kanaType == KanaType.HIRAGANA? clickedSymbol.getHiraganaAscii() : clickedSymbol.getKatakanaAscii(), true);
        }
    };

    private LoaderManager.LoaderCallbacks<Cursor> kanaLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            String selection = Kana.TYPE + " = ?";
            String[] selectArgs = new String[]{String.valueOf(kanaSet.getId())};
            return new CursorLoader(getActivity(), LocalDataProvider.KANA_URI, null, selection, selectArgs, null);
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            int columnCount = kanaSet == Kana.Set.YOUON ? 3 : 5;
            kanaGrid.setNumColumns(columnCount);
            kanaAdapter.setCursor(data, kanaType);
            Logging.d("Loaded " + data.getCount() + " symbols");
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };
}
