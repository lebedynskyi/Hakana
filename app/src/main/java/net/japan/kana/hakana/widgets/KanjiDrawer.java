package net.japan.kana.hakana.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;

import net.japan.kana.hakana.tools.handwriter.HandWriter;
import net.japan.kana.hakana.tools.parser.KanjiVGParser;
import net.japan.kana.hakana.tools.Logging;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class KanjiDrawer extends View {
    public static final String TRACK_TAG = "KanjiDrawer";

    private HandWriter mHandWriter;
    private String mAssetFile;
    private float mScale;

    public KanjiDrawer(Context context) {
        super(context);
    }

    public KanjiDrawer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KanjiDrawer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setKanjiFile(@NonNull String file) {
        mAssetFile = file;
        try {
            KanjiVGParser parser = new KanjiVGParser(mAssetFile, getContext());
            parser.parse();

            mHandWriter = new HandWriter(getContext(), mScale);
            mHandWriter.setPath(parser.getPath());
            invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            Logging.trackException(getContext(), e);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (mHandWriter == null) {
            Logging.e(TRACK_TAG, "mKanjiPath == null || mHandWriter == null");
            return;
        }

        mHandWriter.draw(canvas);

        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if (width > height) {
            width = height;
        }

        setMeasuredDimension(width, width);
        this.mScale = width / 109f;
    }

    private Runnable poster = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };
}
