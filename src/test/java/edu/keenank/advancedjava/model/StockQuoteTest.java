package edu.keenank.advancedjava.model;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * StockQuoteTest class
 */
@Immutable
public class StockQuoteTest {
    private String symbol;
    private StockQuote stockQuote;
    private BigDecimal price;
    private Date date;

    @Before
    public void setUp() {
        symbol = "AAPL";
        price = new BigDecimal(100);
        date = new Date();
        stockQuote = new StockQuote(price, date, symbol);
    }

    @Test
    public void testGetSymbolPositive() {
        assertTrue("Stock symbol is incorrect",
                stockQuote.getSymbol().equals(symbol));
    }

    @Test
    public void testGetSymbolNegative() {
        assertFalse("Stock symbol matches when it shouldn't",
                stockQuote.getSymbol().equals(symbol.toLowerCase()));
    }

    @Test
    public void testGetPricePositive() {
        assertTrue("Price is incorrect",
                stockQuote.getPrice() == price);
    }

    @Test
    public void testGetPriceNegative() {
        assertFalse("Price matches when it shouldn't",
                stockQuote.getPrice() == price.movePointLeft(5));
    }

    @Test
    public void testGetDatePositive() {
        assertTrue("Date is incorrect",
                stockQuote.getDate().equals(date));
    }

    @Test
    public void testGetDateNegative() {
        Date badDate = new Date(1);
        assertFalse("Date matches when it shouldn't",
                stockQuote.getDate().equals(badDate));
    }
}


