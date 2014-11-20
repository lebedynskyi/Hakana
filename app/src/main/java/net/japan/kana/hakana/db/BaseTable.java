package net.japan.kana.hakana.db;

import android.database.Cursor;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public abstract class BaseTable {
    public static final String ID = "_id";
    private int mId;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public abstract void fillByCursor(Cursor c);
}
