package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import net.japan.kana.hakana.tools.KanjiVGParser;
import net.japan.kana.hakana.tools.Tracking;

import java.util.List;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class KanjiDrawer extends View{
    public static final String TRACK_TAG = "SVGKanaDrawer";

    private String mAssetFile;
    private List<Path> mPathes;
    private Paint mPaint;

    public KanjiDrawer(Context context){
        super(context);
    }

    public KanjiDrawer(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public KanjiDrawer(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    public void setKanjiFile(@NonNull String file){
        mAssetFile = file;
        preparePath();
        invalidate();
    }

    private void preparePath(){
        try{
            KanjiVGParser parser = new KanjiVGParser(mAssetFile, getContext());
            parser.parse();
            mPathes = parser.getPathes();
        }catch(Exception e){
            e.printStackTrace();
            Tracking.trackException(TRACK_TAG, "Unable to parse file " + mAssetFile, getContext());
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        //TODO draw empty symbol
        if(mPathes == null || mPathes.isEmpty()){
           return;
        }

        for(Path p : mPathes){
            canvas.drawPath(p, mPaint);
        }
    }
}
