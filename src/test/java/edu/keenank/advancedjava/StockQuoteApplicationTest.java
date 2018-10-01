package edu.keenank.advancedjava;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import java.text.ParseException;

/**
 * StockQuoteApplicationTest class
 */
@Immutable
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
    public void testMainEnumShouldReturnValidResult() throws ParseException {

        String symbol = "AAPL";
        String from = "01/01/2018";
        String until = "12/01/2018";
        String interval = "HOURLY";
        StockQuoteApplication.main(new String[] {symbol, from, until, interval});
    }
}
