package edu.keenank.advancedjava;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 *
 * JUNit test for CurrencyConverter class
 *
 * @author Kevin Keenan
 */
public class CurrencyConverterTest {
    private BigDecimal dollars;
    private BigDecimal euros;
    private BigDecimal rate;

    /**
     * This code is used to setup a known state or baseline
     * It is executed before every test
     */
   @Before
    public void setup() {
        dollars = new BigDecimal(10);
        euros = new BigDecimal(50);
        rate = new BigDecimal(0.88);
    }
    /**
     * Tests the CurrencyConverter Construction
     */
    @Test
    public void testCurrencyConverterConstruction() {
        CurrencyConverter currencyconverter = new CurrencyConverter(dollars, euros, rate);

        /* NOTICE: I always put a descriptive string in front of my assert method.
         * You don't have to there is a version of assertEquals that does not take the String argument.
         * However, these descriptive strings are really helpful for debugging failing tests.
         * Get in the habit of always using them.
         */
        assertEquals("The dollars are correct", currencyconverter.getDollars(), dollars);
        assertEquals("The euros are correct",  currencyconverter.getEuros(), euros);
        assertEquals("The rate is correct", currencyconverter.getRate(), rate);
    }
    /**
     * Tests the Dollar Converter
     */
    @Test
    public void testConvertDollars() {
        CurrencyConverter D = new CurrencyConverter(dollars, euros, rate);
        assertEquals("The conversion is correct", D.ConvertDollars(), 8.8,0.0);
    }
    /**
     * Tests the Negative Dollar Converter
     */
    @Test
    public void testNegativeConvertDollars() {
        CurrencyConverter D = new CurrencyConverter(dollars, euros, rate);
        assertNotEquals("The conversion is incorrect", D.ConvertDollars(), 1.8);
    }
    /**
     * Tests the Euro Converter
     */
    @Test
    public void testConvertEuros() {
        CurrencyConverter E = new CurrencyConverter(dollars, euros, rate);
        assertEquals("The conversion is correct", E.ConvertEuros(), 56.82,0.0);
    }
    /**
     * Tests the Negative Euro Converter
     */
    @Test
    public void testNegativeConvertEuros() {
        CurrencyConverter E = new CurrencyConverter(dollars, euros, rate);
        assertNotEquals("The conversion is incorrect", E.ConvertEuros(), 5,0.0);
    }
}
