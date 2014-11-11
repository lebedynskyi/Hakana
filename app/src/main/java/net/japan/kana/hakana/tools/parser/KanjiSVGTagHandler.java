package net.japan.kana.hakana.tools.parser;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import net.japan.kana.hakana.tools.Logging;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Author Vitalii Lebedynskyi
 * Date 09.11.14
 */
public class KanjiSVGTagHandler extends DefaultHandler {
    private List<Path> parsedPathes;
    private Point parsedSymbolSize;

    public List<Path> getParsedPathes() {
        return parsedPathes;
    }

    public Point getParsedSymbolSize() {
        return parsedSymbolSize;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        Logging.d("SVG Parser", "Start of document, parsing....");
        parsedPathes = new LinkedList<Path>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equalsIgnoreCase("svg")) {
            parsedSymbolSize = new Point(Integer.parseInt(attributes.getValue("height")), Integer.parseInt(attributes.getValue("width")));
        } else if (localName.equalsIgnoreCase("path")) {
            Path p = parseSvgPath(attributes.getValue("d"));
            parsedPathes.add(p);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        Logging.d("SVG Parser", "endElement = " + uri + " localName = " + localName + " qName = " + qName);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        Logging.d("SVG Parser", "End document, creating of path");
    }

    //parsing data of path
    private Path parseSvgPath(String data) {
        Pattern pattern = Pattern.compile("[a-zA-Z]|(-?[\\d+\\.]+)");
        Matcher m = pattern.matcher(data);
        ArrayList<String> groups = new ArrayList<String>();
        while (m.find()) {
            groups.add(m.group());
        }

        Path resultPath = new Path();

        //C x1 y1, x2 y2, x y (or c dx1 dy1, dx2 dy2, dx dy)
        //S x2 y2, x y (or s dx2 dy2, dx dy)  so i think x1 y1 is last point
        float lastX = 0, lastY = 0;
        Iterator<String> groupIterator = groups.iterator();
        while (groupIterator.hasNext()) {
            String token = groupIterator.next();
            if (token.equals("M")) {
                float x = Float.valueOf(groupIterator.next());
                float y = Float.valueOf(groupIterator.next());
                resultPath.moveTo(x, y);
                lastX = x;
                lastY = y;
            } else if (token.equals("L")) {
                float x = Float.valueOf(groupIterator.next());
                float y = Float.valueOf(groupIterator.next());
                resultPath.lineTo(x, y);
                lastX = x;
                lastY = y;
            } else if (token.equals("C")) {
                float x1 = Float.valueOf(groupIterator.next());
                float y1 = Float.valueOf(groupIterator.next());
                float x2 = Float.valueOf(groupIterator.next());
                float y2 = Float.valueOf(groupIterator.next());
                float x = Float.valueOf(groupIterator.next());
                float y = Float.valueOf(groupIterator.next());
                resultPath.cubicTo(x1, y1, x2, y2, x, y);
                lastX = x;
                lastY = y;
            } else if (token.equals("z")) {
                resultPath.close();
            } else if (token.equals("c")) {
                float dx1 = Float.valueOf(groupIterator.next()) + lastX;
                float dy1 = Float.valueOf(groupIterator.next()) + lastY;
                float dx2 = Float.valueOf(groupIterator.next()) + lastX;
                float dy2 = Float.valueOf(groupIterator.next()) + lastY;
                float dx = Float.valueOf(groupIterator.next()) + lastX;
                float dy = Float.valueOf(groupIterator.next()) + lastY;
                resultPath.cubicTo(dx1, dy1, dx2, dy2, dx, dy);
                lastX = dx;
                lastY = dy;
            } else if (token.equals("s")) { //Not implemented
                float dx2 = Float.valueOf(groupIterator.next()) + lastX;
                float dy2 = Float.valueOf(groupIterator.next()) + lastY;
                float dx = Float.valueOf(groupIterator.next()) + lastX;
                float dy = Float.valueOf(groupIterator.next()) + lastY;
                resultPath.cubicTo(lastX, lastY, dx2, dy2, dx, dy);
                lastX = dx;
                lastY = dy;
            } else if (token.equals("S")) { //Not implemented
                float x2 = Float.valueOf(groupIterator.next());
                float y2 = Float.valueOf(groupIterator.next());
                float x = Float.valueOf(groupIterator.next());
                float y = Float.valueOf(groupIterator.next());
                resultPath.cubicTo(lastX, lastY, x2, y2, x, y);
                lastX = x;
                lastY = y;
            } else {
                throw new RuntimeException("unknown command [" + token + "]");
            }
        }

//        move to first point and close contour
        PathMeasure pathMeasure = new PathMeasure(resultPath, false);
        float[] startPoint = new float[2];
        pathMeasure.getPosTan(0, startPoint, null);
        resultPath.moveTo(startPoint[0], startPoint[1]);
        resultPath.close();
        return resultPath;
    }
}