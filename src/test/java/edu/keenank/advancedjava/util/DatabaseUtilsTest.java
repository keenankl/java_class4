package edu.keenank.advancedjava.util;

import edu.keenank.advancedjava.utl.DatabaseInitializationException;
import edu.keenank.advancedjava.utl.DatabaseUtils;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static edu.keenank.advancedjava.utl.DatabaseUtils.initializationFile;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *  Tests for the DatabaseUtils class
 */
public class DatabaseUtilsTest {

    @Before
    public final void setUp() throws DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(initializationFile);
    }

    @Test
    public void testGetConnection() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        assertNotNull("verify that we can get a connection ok",connection);
    }

    @Test
    public void testGetConnectionWorks() throws Exception{
        Connection connection = DatabaseUtils.getConnection();
        Statement statement = connection.createStatement();
        boolean execute = statement.execute("select * from quotes");
        assertTrue("verify that we can execute a statement",execute);
    }
}
