package edu.keenank.advancedjava;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Container class for the stock quote
 */
public class StockQuote {
    private String symbol;
    private BigDecimal price;
    private Date date;

    /**
     * Constructs a new StockQuote
     * @param symbol stock symbol
     * @param price stock price
     * @param date stock date
     */
    public StockQuote(String symbol, BigDecimal price, Date date) {
        this.symbol = symbol;
        this.price = price;
        this.date = date;
    }

    /**
     * @return the stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }


}
