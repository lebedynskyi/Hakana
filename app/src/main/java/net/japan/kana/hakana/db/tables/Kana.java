package net.japan.kana.hakana.db.tables;

import android.database.Cursor;
import net.japan.kana.hakana.db.BaseTable;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class Kana extends BaseTable{
    public static final String TABLE = "Kana";
    public static final String HIRAGANA = "hiragana";
    public static final String KATAKANA = "katakana";
    public static final String EN = "en";
    public static final String TYPE = "type";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE + " (" + ID + " INTEGER PRIMARY KEY," + HIRAGANA + " TEXT," + KATAKANA + " TEXT," + EN + " TEXT," + TYPE + " INTEGER)";

    public static enum Set {
        SEION(1), DAKYOUN(2), YOUON(3);
        int id;

        Set(int value) {
            this.id = value;
        }

        public int getId() {
            return id;
        }
    }

    private char hiragana;
    private char katakana;
    private String en;
    private Set type;

    public Kana(){

    }

    public Kana(char hiragana, char katakana, String en, final int type) {
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.en = en;
        setType(type);

        if(this.type == null){
            throw new IllegalArgumentException("Cannot find type with id " + type);
        }
    }

    @Override
    public void fillByCursor(Cursor c) {
        char hiragana  = c.getString(c.getColumnIndex(Kana.HIRAGANA)).charAt(0);
        char katakana  = c.getString(c.getColumnIndex(Kana.KATAKANA)).charAt(0);
        String en = c.getString(c.getColumnIndex(Kana.EN));
        int type = c.getInt(c.getColumnIndex(Kana.TYPE));

        setHiragana(hiragana);
        setKatakana(katakana);
        setEn(en);
    }


    public char getHiragana() {
        return hiragana;
    }

    public char getKatakana() {
        return katakana;
    }

    public String getEn() {
        return en;
    }

    public void setHiragana(char hiragana) {
        this.hiragana = hiragana;
    }

    public void setKatakana(char katakana) {
        this.katakana = katakana;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getHiraganaAscii() {
        return "0" + Integer.toHexString((int) hiragana);
    }

    public String getKatakanaAscii() {
        return "0" + Integer.toHexString((int) katakana);
    }

    public void setType(int type) {
        for (Set tp : Set.values()){
            if(tp.getId() == type){
                this.type = tp;
                break;
            }
        }
    }
}
