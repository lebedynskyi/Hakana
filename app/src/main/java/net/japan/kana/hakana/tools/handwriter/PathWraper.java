package net.japan.kana.hakana.tools.handwriter;

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Author Vitalii Lebedynskyi
 * Date 09.11.14
 */
public class PathWraper {
    private Path mPath;
    private float mLength;
    private PathMeasure mMeasure;

    public PathWraper(Path path) {
        this.mPath = mPath;
        mMeasure = new PathMeasure(mPath, false);
        mLength = mMeasure.getLength();
    }

    public float getLength() {
        return mLength;
    }

    public Path getPart(float offset){
        Path p = new Path();
        mMeasure.getSegment(0, offset, p, true);
        return p;
    }
}
