package edu.keenank.advancedjava;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * A Class that coverts US dollars to Euros or Euros to Dollars
 *
 * @author Kevin Keenan
 */
public class CurrencyConverter {
    private BigDecimal dollars;
    private BigDecimal euros;
    private BigDecimal rate;

    /**
     * Creates a new CurrencyConverter instance.
     *
     * @param dollars            the number of dollars
     * @param euros              the number of euros
     * @param rate               the conversion rate from dollars to euros
     */
    public CurrencyConverter(BigDecimal dollars, BigDecimal euros, BigDecimal rate) {
        this.dollars = dollars;
        this.euros = euros;
        this.rate = rate;

    }
    /**
     * @return dollars converted by the rate
     */
    public double ConvertDollars() {
       BigDecimal total = dollars.multiply(rate);
       total = total.setScale(2, RoundingMode.HALF_UP);
       double d = total.doubleValue();
       return d;

    }
    /**
     * @return euros converted by the rate
     */
    public double ConvertEuros() {
        BigDecimal total = euros.divide(rate, 2, RoundingMode.HALF_UP);
        double d = total.doubleValue();
        return d;
    }

    /**
     * @return the dollars
     */
    public BigDecimal getDollars() {
        return dollars;
    }
    /**
     * @return the euros
     */
    public BigDecimal getEuros() {
        return euros;
    }
    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }
}
