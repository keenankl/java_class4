package edu.keenank.advancedjava;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * BasicStockServiceTest class
 */
public class BasicStockServiceTest {
    private BasicStockService basicStockService;
    private String symbol;


    /**
     * The initial setup
     */
    @Before
    public void setup() {
        basicStockService = (BasicStockService) StockServiceFactory.getStockService();
        symbol = "AAPL";
    }

    /**
     * Tests that it has the correct symbol
     */
    @Test
    public void testGetQuotePositive() {
        assertTrue("Stock symbol is incorrect",
                basicStockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    /**
     * Tests that it has the incorrect symbol
     */
    @Test
    public void testGetQuoteNegative() {
        assertFalse("Stock symbol matches when it shouldn't",
                basicStockService.getQuote(symbol).getSymbol().equals(symbol.toLowerCase()));
    }
}
