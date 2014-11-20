package net.japan.kana.hakana.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import net.japan.kana.hakana.db.tables.Kana;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class LocalDataProvider extends ContentProvider {
    public static final String AUTH = "net.japan.kana.hakana.db.LocalDataProvider";
    public static final String BASE_PATH = "content://" + AUTH;
    public static final String KANA_PATH = BASE_PATH + "/" + Kana.TABLE;

    private static final int ALL_KANA_ID = 100;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTH, KANA_PATH, ALL_KANA_ID);
    }

    private SQLiteDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new SQLiteDBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projections, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        String groupBy = null;
        switch (uriMatcher.match(uri)){
            case ALL_KANA_ID:
                queryBuilder.setTables(Kana.TABLE);
                break;
            default:
                throw new IllegalArgumentException("Unknown uri " + uri);
        }
        return queryBuilder.query(dbHelper.getWritableDatabase(), projections, selection, selectionArgs, groupBy, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        throw new IllegalStateException("getType() is not implemented  in LocalDataProvider");
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new IllegalStateException("insert() is not implemented  in LocalDataProvider");
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        throw new IllegalStateException("delete() is not implemented  in LocalDataProvider");
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        throw new IllegalStateException("update() is not implemented  in LocalDataProvider");
    }
}
