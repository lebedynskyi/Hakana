package net.japan.kana.hakana;

import android.test.InstrumentationTestCase;

import net.japan.kana.hakana.tools.parser.KanjiVGParser;

/**
 * Author Vitalii Lebedynskyi
 * Date 10/27/14
 */
public class ToolsTest extends InstrumentationTestCase {
    public void testKanjiSVGParser() throws Exception {
        KanjiVGParser parser = new KanjiVGParser("test_kanjivg_file", getInstrumentation().getContext());
        parser.parse();
        assertNotNull(parser.getSymbolSize());
        assertEquals(109, parser.getSymbolSize().x);
        assertEquals(109, parser.getSymbolSize().y);

        assertNotNull(parser.getPath());

        //TODO assert equals
    }
}
