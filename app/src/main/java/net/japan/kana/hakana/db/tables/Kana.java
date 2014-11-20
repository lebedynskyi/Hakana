package net.japan.kana.hakana.db.tables;

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

    public static enum Type{
        SEION(1), DAKYOUN(2), YOUON(3);
        int id;

        Type(int value) {
            this.id = value;
        }

        public int getId() {
            return id;
        }
    }

    private char hiragana;
    private char katakana;
    private String en;
    private Type type;

    private Kana(char hiragana, char katakana, String en, final int type) {
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.en = en;
        for (Type tp : Type.values()){
            if(tp.getId() == type){
                this.type = tp;
                break;
            }
        }

        if(this.type == null){
            throw new IllegalArgumentException("Cannot find type with id " + type);
        }
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
}
