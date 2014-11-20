package net.japan.kana.hakana.core;

import net.japan.kana.hakana.BuildConfig;
import net.japan.kana.hakana.models.KanaSymbol;

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

    public static class Kana {
        private static KanaSymbol[] seionKana;

        static {
            seionKana = new KanaSymbol[]{
                    new KanaSymbol("あ", "ア", "a"),
                    new KanaSymbol("い", "イ", "i"),
                    new KanaSymbol("う", "ウ", "u"),
                    new KanaSymbol("え", "エ", "e"),
                    new KanaSymbol("お", "オ", "o"),
                    new KanaSymbol("か", "カ", "ka"),
                    new KanaSymbol("き", "キ", "ki"),
                    new KanaSymbol("く", "ク", "ku"),
                    new KanaSymbol("け", "ケ", "ke"),
                    new KanaSymbol("こ", "コ", "ko"),
                    new KanaSymbol("さ", "サ", "sa"),
                    new KanaSymbol("し", "シ", "shi"),
                    new KanaSymbol("す", "ス", "su"),
                    new KanaSymbol("せ", "セ", "se"),
                    new KanaSymbol("そ", "ソ", "so"),
                    new KanaSymbol("た", "タ", "ta"),
                    new KanaSymbol("ち", "チ", "chi"),
                    new KanaSymbol("つ", "ツ", "tsu"),
                    new KanaSymbol("て", "テ", "te"),
                    new KanaSymbol("と", "ト", "to"),
                    new KanaSymbol("な", "ナ", "na"),
                    new KanaSymbol("に", "ニ", "ni"),
                    new KanaSymbol("ぬ", "ヌ", "nu"),
                    new KanaSymbol("ね", "ネ", "ne"),
                    new KanaSymbol("の", "ノ", "no"),
                    new KanaSymbol("は", "ハ", "ha"),
                    new KanaSymbol("ひ", "ヒ", "hi"),
                    new KanaSymbol("ふ", "フ", "fu"),
                    new KanaSymbol("へ", "ヘ", "he"),
                    new KanaSymbol("ほ", "ホ", "ho"),
                    new KanaSymbol("ま", "マ", "ma"),
                    new KanaSymbol("み", "ミ", "mi"),
                    new KanaSymbol("む", "ム", "mu"),
                    new KanaSymbol("め", "メ", "me"),
                    new KanaSymbol("も", "モ", "mo"),
                    new KanaSymbol("や", "ヤ", "ya"),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("ゆ", "ユ", "yu"),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("よ", "ヨ", "yo"),
                    new KanaSymbol("ら", "ラ", "ra"),
                    new KanaSymbol("り", "リ", "ri"),
                    new KanaSymbol("る", "ル", "ru"),
                    new KanaSymbol("れ", "レ", "re"),
                    new KanaSymbol("ろ", "ロ", "ro"),
                    new KanaSymbol("わ", "ワ", "wa"),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("を", "ヲ", "wo"),
                    new KanaSymbol("ん", "ン", "n"),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("", "", ""),
                    new KanaSymbol("", "", "")
            };
        }

        public static KanaSymbol[] getSeionKana() {
            return seionKana;
        }
    }
}
