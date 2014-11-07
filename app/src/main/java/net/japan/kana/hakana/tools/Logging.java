package net.japan.kana.hakana.tools;

import android.content.Context;
import android.util.Log;

import net.japan.kana.hakana.core.Const;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class Logging {
    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (Const.Core.LOGABLE) {
            Log.w(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (Const.Core.LOGABLE) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        e(Const.Core.DEFAULT_LOG_TAG, msg);
    }

    public static void w(String msg) {
        w(Const.Core.DEFAULT_LOG_TAG, msg);
    }

    public static void d(String msg) {
        d(Const.Core.DEFAULT_LOG_TAG, msg);
    }

    //TODO implement
    public static void trackException(Context context, Exception e) {

    }
}
