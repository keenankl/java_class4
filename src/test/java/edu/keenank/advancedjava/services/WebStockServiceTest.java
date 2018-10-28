package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.model.StockQuote;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for WebStockService
 */
public class WebStockServiceTest {
    private WebStockService webStockService;
    private String symbol;
    private DateTime date;
    private Calendar from;
    private Calendar until;
    private IntervalEnum interval;

    @Before
    public final void setUp() throws StockServiceException {
        webStockService = (WebStockService) ServiceFactory.getStockServiceInstance(ServiceType.WEB);
        symbol = "AAPL";
        BigDecimal price = BigDecimal.valueOf(100.0);
        from = Calendar.getInstance();
        from.set(2018, 01, 01);
        until = Calendar.getInstance();
        until.set(2018, 1, 8);
        date = DateTime.now();
        interval = IntervalEnum.DAILY;
    }

    @Test
    public final void testGetQuoteSingleArgStockSymbolPositive() throws StockServiceException {
        assertTrue("Symbol returned is not the same", webStockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    @Test
    public final void testGetQuoteSingleArgStockSymbolNegative() throws StockServiceException {
        assertFalse("Symbol returned is the same when it shouldn't be", webStockService.getQuote(symbol).getSymbol().equals(""));
    }

    @Test
    public final void testGetQuoteSingleArgTimePositive() throws StockServiceException {
        assertTrue("Time returned is not the same", !webStockService.getQuote(symbol).getDate().isAfter(DateTime.now()));
    }

    @Test
    public final void testGetQuoteSingleArgTimeNegative() throws StockServiceException {
        assertFalse("Time returned is the same when it shouldn't be", webStockService.getQuote(symbol).getDate().getMillis() == (DateTime.now().getMillis() + 1000));
    }

    @Test
    public final void testGetQuoteTripleArgStockSymbolPositive() throws StockServiceException {
        List<StockQuote> qList = webStockService.getQuote(symbol, from, until);
        for (StockQuote q : qList) {
            assertTrue("Symbol returned is not the same", q.getSymbol().equals(symbol));
        }
    }

    @Test
    public final void testGetQuoteTripleArgStockSymbolNegative() throws StockServiceException {
        List<StockQuote> qList = webStockService.getQuote(symbol, from, until);
        for (StockQuote q : qList) {
            assertFalse("Symbol returned is the same when it shouldn't be", q.getSymbol().equals(""));
        }
    }

    @Test
    public final void testGetQuoteQuadArgStockSymbolPositive() throws StockServiceException {
        List<StockQuote> qList = webStockService.getQuote(symbol, from, until, interval);
        System.out.println(interval.toString());
        for (StockQuote q : qList) {
            assertTrue("Symbol returned is not the same", q.getSymbol().equals(symbol));
        }
    }

    @Test
    public final void testGetQuoteQuadArgStockSymbolNegative() throws StockServiceException {
        List<StockQuote> qList = webStockService.getQuote(symbol, from, until, interval);
        for (StockQuote q : qList) {
            assertFalse("Symbol returned is the same when it shouldn't be", q.getSymbol().equals(""));
        }
    }

}
