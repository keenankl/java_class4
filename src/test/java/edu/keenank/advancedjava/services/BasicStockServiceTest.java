package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.model.StockQuote;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * BasicStockServiceTest class
 */
@Immutable
public class BasicStockServiceTest {
    private BasicStockService basicStockService;
    private String symbol;
    private Date date;
    private Calendar from;
    private Calendar until;
    private IntervalEnum interval;


    /**
     * The initial setup
     */
    @Before
    public void setup() throws StockServiceException {
        basicStockService = (BasicStockService) StockServiceFactory.getInstance(ServiceType.BASIC);
        symbol = "AAPL";
        BigDecimal price = BigDecimal.valueOf(100.0);
        from = Calendar.getInstance();
        from.set(2018, 01, 01);
        until = Calendar.getInstance();
        until.set(2018, 1, 8);
        date = new Date();
        interval = IntervalEnum.DAILY;
    }

    /**
     * Tests that it has the correct symbol
     */
    @Test
    public void testGetQuotePositive() throws StockServiceException {
        assertTrue("Stock symbol is incorrect",
                basicStockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    /**
     * Tests that it has the incorrect symbol
     */
    @Test
    public void testGetQuoteNegative() throws StockServiceException {
        assertFalse("Stock symbol matches when it shouldn't",
                basicStockService.getQuote(symbol).getSymbol().equals(symbol.toLowerCase()));
    }
    /**
     * Tests that it has the correct date
     */
    @Test
    public void testGetQuoteDatePositive() throws StockServiceException {
        assertTrue("Date does not match",
                basicStockService.getQuote(symbol).getDate().equals(date));
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
                basicStockService.getQuote(symbol).getDate().equals(date));
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

        List<StockQuote> stockQuote = basicStockService.getQuote(symbol, from, until);

        assertNotNull("stockQuote is null",stockQuote);
    }

    /**
     * Tests that the HOURLY interval is correct
     */
    @Test
    public void testGetQuoteIntervalPositiveHourly() throws StockServiceException {
        List<StockQuote> stockList = basicStockService.getQuote(symbol, from, until, IntervalEnum.HOURLY);
        assertTrue("The number of quotes is 168 for 7 days", stockList.size() == 168);
    }

    /**
     * Tests that the HOURLY interval is incorrect
     */
    @Test
    public void testGetQuoteIntervalNegativeHourly() throws StockServiceException {
        List<StockQuote> stockList = basicStockService.getQuote(symbol, from, until, IntervalEnum.HOURLY);
        assertFalse("The number of quotes is not greater than or less than 168 for 7 days",
                stockList.size() < 168 || stockList.size() > 168);
    }

    /**
     * Tests that the DAILY interval is correct
     */
    @Test
    public void testGetQuoteIntervalPositiveDaily() throws StockServiceException {
        List<StockQuote> stockList = basicStockService.getQuote(symbol, from, until, IntervalEnum.DAILY);
        assertTrue("The number of quotes is 7 for 7 days", stockList.size() == 7);
    }

    /**
     * Tests that the DAILY interval is incorrect
     */
    @Test
    public void testGetQuoteIntervalNegativeDaily() throws StockServiceException {
        List<StockQuote> stockList = basicStockService.getQuote(symbol, from, until, IntervalEnum.DAILY);
        assertFalse("The number of quotes is not greater than or less than 7 for 7 days",
                stockList.size() < 7 || stockList.size() > 7);
    }

    /**
     * Tests that the WEEKLY interval is correct
     */
    @Test
    public void testGetQuoteIntervalPositiveWeekly() throws StockServiceException {
        List<StockQuote> stockList = basicStockService.getQuote(symbol, from, until, IntervalEnum.WEEKLY);
        assertTrue("The number of quotes is 1 for 7 days", stockList.size() == 1);
    }

    /**
     * Tests that the WEEKLY interval is incorrect
     */
    @Test
    public void testGetQuoteIntervalNegativeWeekly() throws StockServiceException {
        List<StockQuote> stockList = basicStockService.getQuote(symbol, from, until, IntervalEnum.WEEKLY);
        assertFalse("The number of quotes is not greater than or less than 1 for 7 days",
                stockList.size() < 1 || stockList.size() > 1);
    }

}
