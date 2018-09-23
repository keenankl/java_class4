package edu.keenank.advancedjava;

import org.junit.Test;

import java.text.ParseException;

/**
 * StockQuoteApplicationTest class
 */
public class StockQuoteApplicationTest {

    /**
     * Tests for a NullPointerException
     */
    @Test(expected = NullPointerException.class)

    public void testMainNegative() throws ParseException {
        StockQuoteApplication.main(null);
    }

    /**
     * Tests that the main method returns match input
     */
    @Test
    public void testMainShouldReturnValidResult() throws ParseException {

        String symbol = "AAPL";
        String from = "01/01/2018";
        String until = "12/01/2018";
        StockQuoteApplication.main(new String[] {symbol, from, until});
    }
}
