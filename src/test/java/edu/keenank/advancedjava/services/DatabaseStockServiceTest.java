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
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

/**
 * Unit tests for the DatabaseStockService
 */
public class DatabaseStockServiceTest {

    private DatabaseStockService databaseStockService;
    private String symbol;
    private Date date;
    private Calendar from;
    private Calendar until;

    /**
     * The initial setup
     */
    @Before
    public void setup() throws DatabaseConnectionException, StockServiceException, DatabaseInitializationException {
        DatabaseUtils.initializeDatabase(DatabaseUtils.initializationFile);
        databaseStockService = (DatabaseStockService) StockServiceFactory.getInstance(ServiceType.DATABASE);
        symbol = "AAPL";
        BigDecimal price = BigDecimal.valueOf(100.0);
        from = Calendar.getInstance();
        from.set(2018, 01, 01);
        until = Calendar.getInstance();
        until.set(2018, 1, 8);
        date = new Date();
    }

    @Test
    public void testGetQuote() throws Exception {
        DatabaseStockService databaseStockService = new DatabaseStockService();
        String symbol = "AAPL";
        StockQuote stockQuote = databaseStockService.getQuote(symbol);
        assertNotNull("Verify we can get a stock quote from the db", stockQuote);
        assertEquals("Make sure the symbols match", symbol, stockQuote.getSymbol());
    }

    /**
     * Tests that it has the correct symbol
     */
    @Test
    public void testGetQuotePositive() throws StockServiceException {
        assertTrue("Stock symbol is incorrect",
                databaseStockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    /**
     * Tests that it has the incorrect symbol
     */
    @Test
    public void testGetQuoteNegative() throws StockServiceException {
        assertFalse("Stock symbol matches when it shouldn't",
                databaseStockService.getQuote(symbol).getSymbol().equals(symbol.toLowerCase()));
    }
    /**
     * Tests that it has the correct date
     */
    @Test
    public void testGetQuoteDatePositive() throws StockServiceException {
        assertTrue("Date does not match",
                databaseStockService.getQuote(symbol).getDate().equals(date));
    }
    /**
     * Tests that it has the incorrect date
     */
    @Test
    public void testGetQuoteDateNegative() throws StockServiceException {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);
        date = c.getTime();
        assertFalse("Date matches when it shouldn't",
                databaseStockService.getQuote(symbol).getDate().equals(date));
    }
    /**
     * Tests that the list getQuote is not null
     */
    @Test
    public void getListQuotePositive() throws StockServiceException {
        Calendar from = Calendar.getInstance();
        from.setTime(date);

        Calendar until = Calendar.getInstance();
        until.setTime(date);
        until.add(Calendar.DAY_OF_YEAR, 1);

        List<StockQuote> stockQuote = databaseStockService.getQuote(symbol, from, until);

        assertNotNull("stockQuote is null",stockQuote);
    }

    /**
     * Tests that the HOURLY interval is correct
     */
    @Test
    public void testGetQuoteIntervalPositiveHourly() {
        List<StockQuote> stockList = databaseStockService.getQuote(symbol, from, until, IntervalEnum.HOURLY);
        assertTrue("The number of quotes is 168 for 7 days", stockList.size() == 168);
    }

    /**
     * Tests that the HOURLY interval is incorrect
     */
    @Test
    public void testGetQuoteIntervalNegativeHourly() {
        List<StockQuote> stockList = databaseStockService.getQuote(symbol, from, until, IntervalEnum.HOURLY);
        assertFalse("The number of quotes is not greater than or less than 168 for 7 days",
                stockList.size() < 168 || stockList.size() > 168);
    }

    /**
     * Tests that the DAILY interval is correct
     */
    @Test
    public void testGetQuoteIntervalPositiveDaily() {
        List<StockQuote> stockList = databaseStockService.getQuote(symbol, from, until, IntervalEnum.DAILY);
        assertTrue("The number of quotes is 7 for 7 days", stockList.size() == 7);
    }

    /**
     * Tests that the DAILY interval is incorrect
     */
    @Test
    public void testGetQuoteIntervalNegativeDaily() {
        List<StockQuote> stockList = databaseStockService.getQuote(symbol, from, until, IntervalEnum.DAILY);
        assertFalse("The number of quotes is not greater than or less than 7 for 7 days",
                stockList.size() < 7 || stockList.size() > 7);
    }

    /**
     * Tests that the WEEKLY interval is correct
     */
    @Test
    public void testGetQuoteIntervalPositiveWeekly() {
        List<StockQuote> stockList = databaseStockService.getQuote(symbol, from, until, IntervalEnum.WEEKLY);
        assertTrue("The number of quotes is 1 for 7 days", stockList.size() == 1);
    }

    /**
     * Tests that the WEEKLY interval is incorrect
     */
    @Test
    public void testGetQuoteIntervalNegativeWeekly() {
        List<StockQuote> stockList = databaseStockService.getQuote(symbol, from, until, IntervalEnum.WEEKLY);
        assertFalse("The number of quotes is not greater than or less than 1 for 7 days",
                stockList.size() < 1 || stockList.size() > 1);
    }
}
