package net.japan.kana.hakana.models;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/31/14
 */
public class KanaSymbol {
    private String hiragana;
    private String katakana;
    private String englishVersion;

    public KanaSymbol(String hiragana, String katakana, String englishVersion) {
        this.hiragana = hiragana;
        this.katakana = katakana;
        this.englishVersion = englishVersion;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    public String getEnglishVersion() {
        return englishVersion;
    }

    public void setEnglishVersion(String englishVersion) {
        this.englishVersion = englishVersion;
    }

    public String getAscii() {
        return "0" + Integer.toHexString((int) getHiragana().charAt(0));
    }
}
