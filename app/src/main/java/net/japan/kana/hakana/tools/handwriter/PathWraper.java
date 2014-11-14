package net.japan.kana.hakana.tools.handwriter;

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Author Vitalii Lebedynskyi
 * Date 09.11.14
 */
public class PathWraper {
    private float mLength;
    private PathMeasure mMeasure;

    public PathWraper(Path path) {
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
