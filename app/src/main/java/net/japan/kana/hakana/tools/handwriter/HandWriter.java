package net.japan.kana.hakana.tools.handwriter;

import android.content.Context;
import android.graphics.*;
import net.japan.kana.hakana.R;
import net.japan.kana.hakana.tools.ViewUtils;

import java.util.LinkedList;
import java.util.List;


/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class HandWriter {
    //Number of steps to draw symbol
    public static final int STEP_COUNT = 100;

    private List<Path> mPathes;
    private Paint mPaint;

    //Translate and scaling property
    private Matrix mScaleMatrix;
    private Matrix mTranslateMatrix;

    public HandWriter(Context context, float scale) {
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
    }

    //returns true of writer finished
    public boolean draw(Canvas canvas){
        for(Path p : mPathes){
            canvas.drawPath(p, mPaint);
        }
        return false;
    }

    public void setPath(List<Path> path) {
        //Set up current path
        this.mPathes = path;
        for (Path p : mPathes){
            p.transform(this.mTranslateMatrix);
            p.transform(this.mScaleMatrix);
        }
    }
}
