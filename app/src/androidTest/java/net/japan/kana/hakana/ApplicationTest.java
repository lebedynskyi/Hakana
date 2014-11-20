package net.japan.kana.hakana;

import android.os.Environment;
import android.test.ApplicationTestCase;

import net.japan.kana.hakana.core.App;
import net.japan.kana.hakana.core.Const;
import net.japan.kana.hakana.db.SQLiteDBHelper;

import java.io.File;
import java.io.IOException;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<App> {
    public ApplicationTest() {
        super(App.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        createApplication();
    }

    public void testCopyDB(){
        assertEquals(true, getApplication().copyDb(getContext().getDatabasePath(Const.DB.NAME)));
        File dbFile = getApplication().getDatabasePath(Const.DB.NAME);
        assertEquals(true, dbFile.exists());
    }

    public void testGetTracker(){
        assertEquals(true, getApplication().getTracker() != null);
    }

    public void testIsValid() throws IOException {
        testCopyDB();
        SQLiteDBHelper helper = new SQLiteDBHelper(getApplication());
        assertEquals(true, helper.getWritableDatabase() != null);
    }
}