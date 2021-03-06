package net.japan.kana.hakana.core;

import net.japan.kana.hakana.BuildConfig;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public final class Const {
    public static class Core {
        public static final boolean LOGABLE = BuildConfig.DEBUG;
        public static final String DEFAULT_LOG_TAG = "Hakana log>>>>";
    }

    public static class DB {
        public static final int VERSION = 1;
        public static final String NAME = "hakana.db";
    }
}
