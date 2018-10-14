package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.ServiceType;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StockServiceTest class
 */
@Immutable
public class StockServiceTest {
    private String symbol;
    private StockService stockService;
    private Calendar from;
    private Calendar until;


    @Before
    public void setup() throws StockServiceException {
        symbol = "AAPL";
        stockService =  StockServiceFactory.getStockServiceInstance(ServiceType.BASIC);
    }

    @Test
    public void testGetQuotePositive() throws StockServiceException {
        assertTrue("Stock symbol is incorrect",
                stockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    @Test
    public void testGetQuoteNegative() throws StockServiceException {
        assertFalse("Stock symbol matches when it shouldn't",
                stockService.getQuote(symbol).getSymbol().equals(symbol.toLowerCase()));
    }


}
