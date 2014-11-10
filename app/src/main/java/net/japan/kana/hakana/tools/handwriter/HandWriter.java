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

    private Path mPath;
    private PathMeasure mPathMeasure;
    private Paint mPaint;
    private Path mTempPath;

    //Translate and scaling property
    private Matrix mScaleMatrix;
    private Matrix mTranslateMatrix;

    //Draw variables
    private float mCurrentLength; //percent of how much should be drawn
    private float mPathLength; // whole length of path
    private float mStepOffset = 100 / STEP_COUNT;  // 100% divide to STEP_COUNT
    private List<Float> mLengthesOfPathes;//Used for storing parts of integer numbers

    public HandWriter(Context context, float scale) {
        this.mPathMeasure = new PathMeasure(mPath, false);
        this.mTempPath = new Path();

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

        this.mLengthesOfPathes = new LinkedList<Float>();
    }

    //returns true of writer finished
    public boolean draw(Canvas canvas){
        mTempPath.reset();
        mPathMeasure.setPath(mPath, false);

        mCurrentLength += mStepOffset;

        float curLength = mPathLength * mCurrentLength / 100 ; // calculating current part for drawing

        for (int i = 0, size = mLengthesOfPathes.size(); i < size; i++){
            if(mCurrentLength <= mPathMeasure.getLength()){

            }
        }

        mPathMeasure.getSegment(0, mPathMeasure.getLength() + 200, mTempPath, true);
        mTempPath.rLineTo(0, 0); //Hack  - http://developer.android.com/reference/android/graphics/PathMeasure.html#getSegment(float, float, android.graphics.Path, boolean)

        canvas.drawPath(mTempPath, mPaint);
        return mCurrentLength == mPathLength;
    }

    public void setPath(Path path) {
        //Set up current path
        this.mPath = path;
        this.mPath.transform(this.mTranslateMatrix);
        this.mPath.transform(this.mScaleMatrix);

        this.mPathLength = 0;
        this.mCurrentLength = 0;
        this.mLengthesOfPathes.clear();

        //Calculate length of whole path
        this.mPathMeasure.setPath(mPath, false);
        do {
            float contourLength = mPathMeasure.getLength();
            this.mPathLength += contourLength;
            this.mLengthesOfPathes.add(contourLength);
        }while (mPathMeasure.nextContour());
    }
}
