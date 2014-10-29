package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import net.japan.kana.hakana.tools.KanjiVGParser;
import net.japan.kana.hakana.tools.Tracking;

import java.util.List;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class SVGKanaDrawer extends View{
    public static final String TRACK_TAG = "SVGKanaDrawer";

    private String mAssetFile;
    private List<Path> mPathes;
    private Paint mPaint;

    public SVGKanaDrawer(Context context){
        super(context);
        init(context, null);
    }

    public SVGKanaDrawer(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context, attrs);
    }

    public SVGKanaDrawer(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        if(attrs != null){
            //TODO init asset file
            preparePath();
        }
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
        if(mPathes == null || mPathes.isEmpty()){
           return;
        }

        for(Path p : mPathes){
            canvas.drawPath(p, mPaint);
        }
    }
}
