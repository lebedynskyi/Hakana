package net.japan.kana.hakana.tools.handwriter;

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Author Vitalii Lebedynskyi
 * Date 09.11.14
 */
public class PathWrapper {
    private float mLength;
    private PathMeasure mMeasure;

    public PathWrapper(Path path) {
        mMeasure = new PathMeasure(path, false);
        mLength = mMeasure.getLength();
    }

    public float getLength() {
        return mLength;
    }

    public Path getPart(float offset){
        Path p = new Path();
        mMeasure.getSegment(0, offset, p, true);
        p.rLineTo(0, 0); // Hack from developers.android.com
        return p;
    }
}
