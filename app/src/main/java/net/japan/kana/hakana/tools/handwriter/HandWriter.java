package net.japan.kana.hakana.tools.handwriter;

import android.content.Context;
import android.graphics.*;
import net.japan.kana.hakana.R;
import net.japan.kana.hakana.tools.Logging;
import net.japan.kana.hakana.tools.ViewUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class HandWriter {
    private List<PathWraper> mPathes;
    private Paint mPaint;

    //Translate and scaling property
    private Matrix mScaleMatrix;
    private Matrix mTranslateMatrix;

    private float drawOffset;
    private int drawSteepsCount;
    private float lastDrawedlength;
    private float allLength;

    public HandWriter(Context context, float scale, int drawSteepsCount) {
        //Preparing paint
        this.mPaint = new Paint();
        this.mPaint.setColor(context.getResources().getColor(R.color.view_kana_item_text_color));
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(ViewUtils.dpToPx(context, 4));

        int translateOffset = (int) (10.0F / context.getResources().getDisplayMetrics().density);
        this.mTranslateMatrix = new Matrix();
        this.mScaleMatrix = new Matrix();
        this.mTranslateMatrix.setTranslate(scale * translateOffset, 0.0F);
        this.mScaleMatrix.setScale(scale, scale);

        this.drawSteepsCount = drawSteepsCount;
    }

    //returns true of writer finished
    public boolean draw(Canvas canvas){
        if(mPathes == null || mPathes.isEmpty()){
            Logging.w("HandWriter", "mPathes == null || mPathes.isEmpty()");
            return false;
        }

        lastDrawedlength += drawOffset;
        if(lastDrawedlength >= allLength){
            lastDrawedlength = allLength; //Final part of symbol
        }

        float allPassedLength = 0f;
        //TODO not working. Should refactor it
        for(PathWraper p : mPathes){
            if(lastDrawedlength >= p.getLength()){
                allPassedLength += p.getLength();
                canvas.drawPath(p.getPart(p.getLength()), mPaint);
            }else {
                float pathPartOffset = lastDrawedlength - allPassedLength;
                Logging.d("Index = " + mPathes.indexOf(p) + ", Drawing part = " + pathPartOffset);
                canvas.drawPath(p.getPart(pathPartOffset), mPaint);
                break;
            }
        }

        return Float.compare(allLength, lastDrawedlength) == 0;
    }

    public void setPath(List<Path> path) {
        //Set up current path
        mPathes = new LinkedList<PathWraper>();
        allLength = 0;

        for (Path p : path){
            p.transform(this.mTranslateMatrix);
            p.transform(this.mScaleMatrix);
            PathWraper wraper = new PathWraper(p);
            allLength += wraper.getLength();
            mPathes.add(wraper);
        }

        drawOffset = allLength / drawSteepsCount;
        lastDrawedlength = 0f;
    }
}
