package edu.keenank.advancedjava;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StockQuoteTest class
 */
public class StockQuoteTest {
    private String symbol;
    private StockQuote stockQuote;

    /**
     * The initial setup
     */
    @Before
    public void setup() {
        symbol = "AAPL";
        stockQuote = new StockQuote(symbol);
    }

    /**
     * Tests that it has the correct symbol
     */
    @Test
    public void testGetSymbolPositive() {
        assertTrue("Stock symbol is incorrect",
                stockQuote.getSymbol().equals(symbol));
    }

    /**
     * Tests that it has the incorrect symbol
     */
    @Test
    public void testGetSymbolNegative() {
        assertFalse("Stock symbol matches when it shouldn't",
                stockQuote.getSymbol().equals(symbol.toLowerCase()));
    }

}
