package edu.keenank.advancedjava.util;

import edu.keenank.advancedjava.utl.DatabaseInitializationException;
import edu.keenank.advancedjava.utl.DatabaseUtils;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;

import static edu.keenank.advancedjava.utl.DatabaseUtils.initializationFile;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 *  Tests for the DatabaseUtils class
 */
@Immutable
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

    @Test
    public final void testGetSessionFactoryPositive() {
        org.hibernate.Session session = DatabaseUtils.getSessionFactory().openSession();
        assertTrue("getSessionFactory() return value is closed", session.isOpen());
    }

    @Test
    public final void testGetSessionFactoryNegative() {
        Session session = DatabaseUtils.getSessionFactory().openSession();
        assertFalse("getSessionFactory() return value is set to default read-only", session.isDefaultReadOnly());
    }

}
