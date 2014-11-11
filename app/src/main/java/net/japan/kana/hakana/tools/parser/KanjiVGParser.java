package net.japan.kana.hakana.tools.parser;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class KanjiVGParser {
    public static final String LOG_TAG = "KanjiVGParser";

    private final String assetFilePath;
    private final Context context;
    private boolean parsed = false;

    private List<Path> mPath;
    private Point symbolSize;

    public KanjiVGParser(String assetFilePath, Context c) {
        this.assetFilePath = assetFilePath;
        this.context = c;
    }

    //preparing of path for kanji
    public void parse() throws Exception {
        InputStream stream = context.getAssets().open(assetFilePath);
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            KanjiSVGTagHandler tagHandler = new KanjiSVGTagHandler();
            parser.parse(stream, tagHandler);
            mPath = tagHandler.getParsedPathes();
            symbolSize = tagHandler.getParsedSymbolSize();
            parsed = true;
        } finally {
            try {
                stream.close();
            } catch (Exception ignored) {
            }
        }
    }

    //returns parsed mPath
    public List<Path> getPath() {
        if (!parsed) {
            throw new IllegalStateException("parse() should be called before");
        }

        return mPath;
    }

    //returns parsed size of parsed symbol
    public Point getSymbolSize() {
        if (!parsed) {
            throw new IllegalStateException("parse() should be called before");
        }

        return symbolSize;
    }
}
