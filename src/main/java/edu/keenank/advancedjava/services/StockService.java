package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.model.StockQuote;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.Calendar;
import java.util.List;

/**
 *  This interface describes an API for getting stock data.
 */
@Immutable
public interface StockService {
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
    StockQuote getQuote(String symbol) throws StockServiceException;

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
    List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException;

    /** Get a historical list of stock quotes for the
     * provide symbol
     * @param symbol the stock symbol to search for
     * @param from the date of the first stock quote
     * @param until the date of the last stock quote
     * @return a list of StockQuote instances. One for each day
     * in the range specified.
     */
    List<StockQuote> getQuote(String symbol, Calendar from,
                              Calendar until, IntervalEnum interval) throws StockServiceException;
}