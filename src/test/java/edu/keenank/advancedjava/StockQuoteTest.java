package edu.keenank.advancedjava;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StockQuoteTest class
 */
public class StockQuoteTest {
    private String symbol;
    private StockQuote stockQuote;
    private BigDecimal price;
    private Date date;

    /**
     * The initial setup
     */
    @Before
    public void setUp() {
        symbol = "AAPL";
        price = new BigDecimal(100);
        date = new Date();
        stockQuote = new StockQuote(symbol, price, date);
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

    /**
     * Verifies that the correct stock price is returned
     */
    @Test
    public void testGetPricePositive() {
        assertTrue("Price is incorrect",
                stockQuote.getPrice() == price);
    }

    /**
     * Verifies that an incorrect stock price is returned
     */
    @Test
    public void testGetPriceNegative() {
        assertFalse("Price matches when it shouldn't",
                stockQuote.getPrice() == price.movePointLeft(5));
    }

    /**
     * Verifies that the correct date recorded is returned
     */
    @Test
    public void testGetDatePositive() {
        assertTrue("Date is incorrect",
                stockQuote.getDate().equals(date));
    }

    /**
     * Verifies that an incorrect date recorded is returned
     */
    @Test
    public void testGetDateNegative() {
        Date badDate = new Date(1);
        assertFalse("Date matches when it shouldn't",
                stockQuote.getDate().equals(badDate));
    }
}


