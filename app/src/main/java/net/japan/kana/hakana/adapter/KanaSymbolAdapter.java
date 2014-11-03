package net.japan.kana.hakana.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.models.KanaSymbol;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Author Vitalii Lebedynskyi
 * Date 11/3/14
 */
public class KanaSymbolAdapter extends BaseAdapter{
    public static enum KanaType{
        HIRAGANA, KATKANA
    }

    private final ArrayList<KanaSymbol> mData = new ArrayList<KanaSymbol>();
    private KanaType type;
    private int selectedPosition;

    public KanaSymbolAdapter(KanaType type, List<KanaSymbol> data){
        setData(type, data);
    }

    public void setData(KanaType type, List<KanaSymbol> data){
        this.type = type;
        this.mData.clear();
        this.mData.addAll(data);

        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return mData.size();
    }

    @Override
    public KanaSymbol getItem(int position){
        return mData.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kana_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        KanaSymbol symbol = mData.get(position);
        holder.kanaSymbol.setText(type == KanaType.HIRAGANA ? symbol.getHiragana() : symbol.getKatakana());
        holder.kanaPronounce.setText(symbol.getEnglishVersion());

        return convertView;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        // Return true for clickable, false for not
        return !TextUtils.isEmpty(mData.get(position).getHiragana());
    }

    public void setSelectedPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    static class ViewHolder {
        @InjectView(R.id.kana_pronounce) TextView kanaPronounce;
        @InjectView(R.id.kana_symbol) TextView kanaSymbol;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
