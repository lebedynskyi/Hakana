package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
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

    private List<Path> mPathes;
    private Paint mPaint;
    private Matrix scaleMatrix = new Matrix();
    private Matrix translateMatrix = new Matrix();
    private int scale;
    private String mAssetFile;

    public KanjiDrawer(Context context){
        super(context);
    }

    public KanjiDrawer(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public KanjiDrawer(Context context, AttributeSet attrs, int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setColor(getContext().getResources().getColor(R.color.view_kana_item_text_color));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(ViewUtils.dpToPx(getContext(), 4));
        int translateOffset = (int)(10.0F / getResources().getDisplayMetrics().density);
        this.translateMatrix.setTranslate(this.scale * translateOffset, 0.0F);
        this.scaleMatrix.setScale(scale, scale);
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
        if(mPathes == null || mPathes.isEmpty()){
           return;
        }

        for(Path p : mPathes){
            p.transform(this.translateMatrix);
            p.transform(this.scaleMatrix);
            canvas.drawPath(p, mPaint);
        }
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if(width > height){
            width = height;
        }

        setMeasuredDimension(width, height);
        this.scale = width / 109;
        init();
    }

    public void DrawKanji(){

    }

    public void stopDrawKanji(){

    }
}
