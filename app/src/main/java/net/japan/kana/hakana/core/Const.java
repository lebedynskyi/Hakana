package net.japan.kana.hakana.core;

import net.japan.kana.hakana.models.KanaSymbols;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/24/14
 */
public final class Const{
    public static class Core{
        public static final boolean LOGABLE = true;
        public static final String DEFAULT_LOG_TAG = "Hakana log --- >>>>";
    }
    public static class DB{
        public static final int VERSION = 1;
        public static final String NAME = "Hakana.db";
    }

    public static class Kana{
        private static KanaSymbols[][]seionKana;

        static {
            seionKana = new KanaSymbols[][]{
            new KanaSymbols[]{new KanaSymbols("あ", "ア", "a"),
            new KanaSymbols("い", "イ", "i"),
            new KanaSymbols("う", "ウ", "u"),
            new KanaSymbols("え", "エ", "e"),
            new KanaSymbols("お", "オ", "o")},

            new KanaSymbols[]{new KanaSymbols("か", "カ", "ka"),
            new KanaSymbols("き", "キ", "ki"),
            new KanaSymbols("く", "ク", "ku"),
            new KanaSymbols("け", "ケ", "ke"),
            new KanaSymbols("こ", "コ", "ko")},

            new KanaSymbols[]{new KanaSymbols("さ", "サ", "sa"),
            new KanaSymbols("し", "シ", "shi"),
            new KanaSymbols("す", "ス", "su"),
            new KanaSymbols("せ", "セ", "se"),
            new KanaSymbols("そ", "ソ", "so")},

            new KanaSymbols[]{new KanaSymbols("た", "タ", "ta"),
            new KanaSymbols("ち", "チ", "chi"),
            new KanaSymbols("つ", "ツ", "tsu"),
            new KanaSymbols("て", "テ", "te"),
            new KanaSymbols("と", "ト", "to")},

            new KanaSymbols[]{new KanaSymbols("な", "ナ", "na"),
            new KanaSymbols("に", "ニ", "ni"),
            new KanaSymbols("ぬ", "ヌ", "nu"),
            new KanaSymbols("ね", "ネ", "ne"),
            new KanaSymbols("の", "ノ", "no")},

            new KanaSymbols[]{new KanaSymbols("は", "ハ", "ha"),
            new KanaSymbols("ひ", "ヒ", "hi"),
            new KanaSymbols("ふ", "フ", "fu"),
            new KanaSymbols("へ", "ヘ", "he"),
            new KanaSymbols("ほ", "ホ", "ho")},

            new KanaSymbols[]{new KanaSymbols("ま", "マ", "ma"),
            new KanaSymbols("み", "ミ", "mi"),
            new KanaSymbols("む", "ム", "mu"),
            new KanaSymbols("め", "メ", "me"),
            new KanaSymbols("も", "モ", "mo")},

            new KanaSymbols[]{new KanaSymbols("や", "ヤ", "ya"),
            new KanaSymbols("", "", ""),
            new KanaSymbols("ゆ", "ユ", "yu"),
            new KanaSymbols("", "", ""),
            new KanaSymbols("よ", "ヨ", "yo")},

            new KanaSymbols[]{new KanaSymbols("ら", "ラ", "ra"),
            new KanaSymbols("り", "リ", "ri"),
            new KanaSymbols("る", "ル", "ru"),
            new KanaSymbols("れ", "レ", "re"),
            new KanaSymbols("ろ", "ロ", "ro")},

            new KanaSymbols[]{new KanaSymbols("わ", "ワ", "wa"),
            new KanaSymbols("", "", ""),
            new KanaSymbols("", "", ""),
            new KanaSymbols("", "", ""),
            new KanaSymbols("を", "ヲ", "wo")},

            new KanaSymbols[]{new KanaSymbols("ん", "ン", "n"),
            new KanaSymbols("", "", ""),
            new KanaSymbols("", "", ""),
            new KanaSymbols("", "", ""),
            new KanaSymbols("", "", "")}
            };
        }

        public static KanaSymbols[][] getSeionKana(){
            return seionKana;
        }
    }
}
