package net.japan.kana.hakana.tools;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Author Vitalii Lebedynskyi
 * Date 11/4/14
 */
public class ViewUtils{
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
}
