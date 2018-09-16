package edu.keenank.advancedjava;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StockServiceTest class
 */
public class StockServiceTest {
    private String symbol;
    private StockService stockService;

    /**
     * The initial setup
     */
    @Before
    public void setup() {
        symbol = "AAPL";
        stockService =  StockServiceFactory.getStockService();
    }

    /**
     * Tests that it has the correct symbol
     */
    @Test
    public void testGetQuotePositive() {
        assertTrue("Stock symbol is incorrect",
                stockService.getQuote(symbol).getSymbol().equals(symbol));
    }

    /**
     * Tests that it has the incorrect symbol
     */
    @Test
    public void testGetQuoteNegative() {
        assertFalse("Stock symbol matches when it shouldn't",
                stockService.getQuote(symbol).getSymbol().equals(symbol.toLowerCase()));
    }
}
