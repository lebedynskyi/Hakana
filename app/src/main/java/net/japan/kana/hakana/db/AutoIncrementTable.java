package net.japan.kana.hakana.db;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class AutoIncrementTable{
    public static final String ID = "mId";
    private int mId;

    public int getId(){
        return mId;
    }

    public void setId(int _id){
        this.mId = _id;
    }
}
