package net.japan.kana.hakana.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import net.japan.kana.hakana.R;
import net.japan.kana.hakana.db.tables.Kana;
import net.japan.kana.hakana.fragment.KanaFragment;

/**
 * Created by Vitalii_Lebedynskyi on 11/20/14.
 */
public class KanaCursorAdapter extends CursorAdapter {
    private KanaFragment.KanaType type;

    public KanaCursorAdapter(Context context) {
        super(context, null, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.view_kana_item, parent, false);
        v.setTag(new ViewHolder(v));
        return v;
    }

    @Override
    public boolean isEnabled(int position) {
        // Return true for clickable, false for not
        Cursor c = getCursor();
        c.moveToPosition(position);
        return !TextUtils.isEmpty(c.getString(c.getColumnIndex(Kana.HIRAGANA)));
    }

    public void setCursor(Cursor c, KanaFragment.KanaType type){
        this.type = type;
        changeCursor(c);
    }

    @Override
    public Kana getItem(int position) {
        Cursor c = getCursor();
        c.moveToPosition(position);
        Kana k = new Kana();
        k.fillByCursor(c);
        return k;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        ViewHolder holder = (ViewHolder) view.getTag();

        int index = type == KanaFragment.KanaType.KATAKANA ? cursor.getColumnIndex(Kana.KATAKANA) : cursor.getColumnIndex(Kana.HIRAGANA);
        holder.kanaSymbol.setText(cursor.getString(index));
        holder.kanaPronounce.setText(cursor.getString(cursor.getColumnIndex(Kana.EN)));
    }

    static class ViewHolder {
        @InjectView(R.id.kana_pronounce)
        TextView kanaPronounce;
        @InjectView(R.id.kana_symbol)
        TextView kanaSymbol;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
