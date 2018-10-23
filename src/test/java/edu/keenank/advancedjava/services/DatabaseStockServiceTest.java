package edu.keenank.advancedjava.services;


import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.model.StockQuote;
import edu.keenank.advancedjava.utl.DatabaseConnectionException;
import edu.keenank.advancedjava.utl.DatabaseInitializationException;
import edu.keenank.advancedjava.utl.DatabaseUtils;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Unit tests for the DatabaseStockService
 */
public class DatabaseStockServiceTest {

    private DatabaseStockService databaseStockService;
    private String symbol;
    private Date date;
    private Calendar from;
    private Calendar until;
    private IntervalEnum interval;

    @Before
    public void setup() throws DatabaseConnectionException, StockServiceException, DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
        databaseStockService = (DatabaseStockService) StockServiceFactory.getStockServiceInstance(ServiceType.DATABASE);
        symbol = "AAPL";
        BigDecimal price = BigDecimal.valueOf(100.0);
        from = Calendar.getInstance();
        from.set(2018, 01, 01);
        until = Calendar.getInstance();
        until.set(2018, 1, 8);
        date = new Date();
        interval = IntervalEnum.DAILY;
    }

    @Test
    public void testGetQuote() throws Exception {
        DatabaseStockService databaseStockService = new DatabaseStockService();
        String symbol = "AAPL";
        StockQuote stockQuote = databaseStockService.getQuote(symbol);
        assertNotNull("Verify we can get a stock quote from the db", stockQuote);
        assertEquals("Make sure the symbols match", symbol, stockQuote.getSymbol());
    }

    @Test
    public void testGetQuotePositive() throws StockServiceException {
        assertTrue("Stock symbol is incorrect",
                databaseStockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    @Test
    public void testGetQuoteNegative() throws StockServiceException {
        assertFalse("Stock symbol matches when it shouldn't",
                databaseStockService.getQuote(symbol).getSymbol().equals(symbol.toLowerCase()));
    }

}
