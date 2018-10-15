package edu.keenank.advancedjava.model;


import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;

/**
 * Container class for the stock quote
 */

public class StockQuote extends StockData {
    private BigDecimal price;
    private DateTime date;
    private String symbol;

    public static final String DATE_PATTERN = "YYYY-MM-dd HH:mm:ss";
    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern(DATE_PATTERN);
    /**
     * Create a new instance of a StockQuote.
     *
     * @param price  the share price for the given date
     * @param date   the date of the share price
     * @param symbol the stock symbol.
     */
    public StockQuote(BigDecimal price, DateTime date, String symbol) {
        super();
        this.price = price;
        this.date = date;
        this.symbol = symbol;
    }

    /**
     * @return Get the share price for the given date.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @return The date of the share price
     */
    public DateTime getDate() {
        return date;
    }

    /**
     * @return The stock symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns the information as a string
     */
    @Override
    public String toString() {
        String dateString = simpleDateFormat.format(date);
        return "StockQuote{" +
                "price=" + price +
                ", date=" + dateString +
                ", symbol='" + symbol + '\'' +
                '}';
    }

}
