package net.japan.kana.hakana.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.activity.MainActivity;
import net.japan.kana.hakana.adapter.KanaSymbolAdapter;
import net.japan.kana.hakana.core.BaseActivity;
import net.japan.kana.hakana.core.BaseFragment;
import net.japan.kana.hakana.core.Const;
import net.japan.kana.hakana.models.KanaSymbol;

import java.util.Arrays;
import java.util.List;

import butterknife.InjectView;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/30/14
 */
public class KanaFragment extends BaseFragment<MainActivity>{
    enum KanaSet{
        SEION, DAKUON, YOUON
    }

    private KanaSet setOfKana = KanaSet.SEION;
    private KanaSymbolAdapter kanaAdapter;
    private KanaSymbolAdapter.KanaType currentType = KanaSymbolAdapter.KanaType.HIRAGANA;

    @InjectView(R.id.kana_grid_view)
    GridView kanaGrid;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        kanaAdapter = new KanaSymbolAdapter(currentType, createListOfkanas());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_kana, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        kanaGrid.setAdapter(kanaAdapter);
        kanaGrid.setOnItemClickListener(symbolClickListener);
        setTitle(getString(R.string.hiragana));
    }

    public List<KanaSymbol> createListOfkanas(){
        if(setOfKana == KanaSet.SEION){
            return Arrays.asList(Const.Kana.getSeionKana());
        }else {
            return null;
        }
    }

    private AdapterView.OnItemClickListener symbolClickListener = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            kanaAdapter.setSelectedPosition(position);
        }
    };
}
