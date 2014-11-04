package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import net.japan.kana.hakana.R;
import net.japan.kana.hakana.tools.KanjiVGParser;
import net.japan.kana.hakana.tools.Logging;
import net.japan.kana.hakana.tools.ViewUtils;

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
        init(context);
    }

    public KanjiDrawer(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public KanjiDrawer(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.view_kana_item_text_color));
        mPaint.setStrokeWidth(ViewUtils.dpToPx(context, 3));
        mPaint.setStyle(Paint.Style.STROKE);
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
            Logging.trackException(getContext(), e);
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.save();
        //TODO draw empty symbol
        if(mPathes == null || mPathes.isEmpty()){
           return;
        }

        for(Path p : mPathes){
            canvas.drawPath(p, mPaint);
        }
        canvas.restore();
    }

    public void DrawKanji(){

    }

    public void stopDrawKanji(){

    }
}
