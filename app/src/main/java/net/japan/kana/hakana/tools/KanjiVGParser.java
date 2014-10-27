package net.japan.kana.hakana.tools;

import android.content.Context;
import android.graphics.Path;
import android.graphics.Point;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class KanjiVGParser{
    public static final String LOG_TAG = "KanjiVGParser";
    private final String assetFilePath;
    private final Context context;
    private boolean parsed;
    private LinkedList<Path> pathes;
    private Point symbolSize;

    public KanjiVGParser(String assetFilePath, Context c){
        this.assetFilePath = assetFilePath;
        this.context = c;
    }

    public void parse() throws Exception{
        InputStream stream = context.getAssets().open(assetFilePath);
        try{
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser parser = saxParserFactory.newSAXParser();
            parser.parse(stream, new SVGtagHandler());
        }finally{
            try{
                stream.close();
            }catch(Exception ignored){
            }
        }
        //TODO parse
        parsed = true;
    }

    public List<Path> getPathes(){
        if(!parsed){
            throw new IllegalStateException("parse() should be called before");
        }

        return pathes;
    }

    public Point getSymbolSize(){
        if(!parsed){
            throw new IllegalStateException("parse() should be called before");
        }

        return symbolSize;
    }

    private class SVGtagHandler extends DefaultHandler{
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException{
            super.characters(ch, start, length);
        }

        @Override
        public void startDocument() throws SAXException{
            super.startDocument();
            Logging.d("SVG Parser", "Start of document, parsing....");
            pathes = new LinkedList<Path>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException{
            if(localName.equalsIgnoreCase("svg")){
                symbolSize = new Point(Integer.parseInt(attributes.getValue("height")), Integer.parseInt(attributes.getValue("width")));
            }else if(localName.equalsIgnoreCase("path")){
                pathes.add(parseSvgPath(attributes.getValue("d")));
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException{
            super.endElement(uri, localName, qName);
            Logging.d("SVG Parser", "endElement = " + uri + " localName = " + localName + " qName = " + qName);
        }

        @Override
        public void endDocument() throws SAXException{
            super.endDocument();
            Logging.d("SVG Parser", "End document, creating of path");
        }

        private Path parseSvgPath(String data){
            Path p = new Path();
            Pattern pattern = Pattern.compile("[a-zA-Z]|(-?[\\d+\\.]+)");
            Matcher m = pattern.matcher(data);
            ArrayList<String> groups = new ArrayList<String>();
            while(m.find()){
                groups.add(m.group());
            }

            Iterator<String> groupIterator = groups.iterator();
            while(groupIterator.hasNext()){
                String token = groupIterator.next();
                if(token.equals("M")){
                    float x = Float.valueOf(groupIterator.next());
                    float y = Float.valueOf(groupIterator.next());
                    p.moveTo(x, y);
                }else if(token.equals("L")){
                    float x = Float.valueOf(groupIterator.next());
                    float y = Float.valueOf(groupIterator.next());
                    p.lineTo(x, y);
                }else if(token.equals("C")){
                    float x1 = Float.valueOf(groupIterator.next());
                    float y1 = Float.valueOf(groupIterator.next());
                    float x2 = Float.valueOf(groupIterator.next());
                    float y2 = Float.valueOf(groupIterator.next());
                    float x3 = Float.valueOf(groupIterator.next());
                    float y3 = Float.valueOf(groupIterator.next());
                    p.cubicTo(x1, y1, x2, y2, x3, y3);
                }else if(token.equals("z")){
                    p.close();
                }else if(token.equals("c")){
                    float x1 = Float.valueOf(groupIterator.next());
                    float y1 = Float.valueOf(groupIterator.next());
                    float x2 = Float.valueOf(groupIterator.next());
                    float y2 = Float.valueOf(groupIterator.next());
                    float x3 = Float.valueOf(groupIterator.next());
                    float y3 = Float.valueOf(groupIterator.next());
                    p.cubicTo(x1, y1, x2, y2, x3, y3);
                }else {
                    throw new RuntimeException("unknown command [" + token + "]");
                }
            }
            return p;
        }
    }
}
