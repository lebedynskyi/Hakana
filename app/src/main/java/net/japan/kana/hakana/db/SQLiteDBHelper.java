package net.japan.kana.hakana.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import net.japan.kana.hakana.core.Const;
import net.japan.kana.hakana.db.tables.Kana;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class SQLiteDBHelper extends SQLiteOpenHelper {
    public static final int VERSION = Const.DB.VERSION;
    public static final String NAME = Const.DB.NAME;

    public SQLiteDBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Kana.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
