package edu.keenank.advancedjava;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Implements the interface to define methods for getting stock quotes
 *
 * */
public class BasicStockService implements StockService {

    private StockService basicStockService;

    /**
     * Gets StockQuote instance for a symbol
     *
     * @param symbol stock symbol
     * @return a new StockQuote
     */
    public StockQuote getQuote(String symbol) {

        Date date = new Date();
        BigDecimal price = BigDecimal.valueOf(100.0);
        return new StockQuote ("AAPL", price, date);
    }

    /**
     * Gets StockQuote List instance for a symbol for a defined time period
     *
     * @param symbol stock symbol
     * @param from begining date
     * @param until ending date
     * @return a new StockQuote List
     */
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) {
        List<StockQuote> returnValue = new ArrayList<>();

        while (from.before(until)) {
            StockQuote testQuote = getQuote("AAPL");
            returnValue.add(testQuote);
            from.add(Calendar.DAY_OF_YEAR, 1);

        }

        return returnValue;
    }

}
