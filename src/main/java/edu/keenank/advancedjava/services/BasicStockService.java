package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.model.StockQuote;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Implements the interface to define methods for getting stock quotes
 *
 * */
@Immutable
public class BasicStockService implements StockService {

    private StockService basicStockService;

    /**
     * Gets StockQuote instance for a symbol
     *
     * @param symbol stock symbol
     * @return a new StockQuote
     */
    /**
     * Return the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. APPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    @Override
    public StockQuote getQuote(String symbol) throws StockServiceException {
        // a dead simple implementation.
        return new StockQuote(new BigDecimal(100), Calendar.getInstance().getTime(), symbol);
    }

    /**
     * Get a historical list of stock quotes for the provide symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @return a list of StockQuote instances
     * @throws   StockServiceException if using the service generates an exception.
     * If this happens, trying the service may work, depending on the actual cause of the
     * error.
     */
    @Override
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException {
        // a dead simple implementation.
        List<StockQuote> stockQuotes = new ArrayList<>();
        Date aDay = from.getTime();
        while (until.after(aDay)) {
            stockQuotes.add(new StockQuote(new BigDecimal(100), aDay, symbol));
            from.add(Calendar.DAY_OF_YEAR, 1);
            aDay = from.getTime();
        }
        return stockQuotes;
    }

    /**
     * Gets StockQuote List instance for a symbol for a defined time period and frequency
     *
     * @param symbol stock symbol
     * @param from begining date
     * @param until ending date
     * @param interval frequency of quotes
     * @return a new StockQuote List
     */
    public List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException {
        List<StockQuote> returnValue = new ArrayList<>();

        Calendar start = from;
        Calendar end = until;

        while (start.before(end)) {
            StockQuote testQuote = getQuote("AAPL");
            returnValue.add(testQuote);
            start.add(Calendar.HOUR_OF_DAY, interval.amount());
        }

        return returnValue;
    }


}
