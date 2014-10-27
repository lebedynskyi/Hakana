package net.japan.kana.hakana.db;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public class BaseTable{
    public static final String ID = "_id";
    private int mId;

    public int getId(){
        return mId;
    }

    public void setId(int _id){
        this.mId = _id;
    }
}
