package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.model.StockQuote;
import org.joda.time.DateTime;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static yahoofinance.histquotes.Interval.DAILY;

/**
 * Obtains stock quotes from Yahoo web service
 */
public class WebStockService implements StockService {

    public static final WebStockService webStockService = new WebStockService();

    /**
     * Constructor
     */
    protected WebStockService(){}

    /**
     * Returns today's {@code StockQuote} instance for a symbol
     * @param symbol the stock symbol
     * @return a {@code StockQuote} instance
     * @throws StockServiceException
     */
    public final StockQuote getQuote(String symbol) throws StockServiceException {
        StockQuote stockQuote;
        try {
            Stock stock = YahooFinance.get(symbol);
            stockQuote = new StockQuote(stock.getQuote().getPrice(), new DateTime(stock.getQuote().getLastTradeTime()), stock.getQuote().getSymbol());
        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }
        if (stockQuote == null) {
            throw new StockServiceException("No instances of " + symbol + " found in the selected range");
        }
        return stockQuote;
    }

    /**
     * Returns a list of {@code StockQuote} objects for the stock symbol in an interval
     * Quotes are daily by default
     *
     * @param symbol the stock symbol
     * @param from the start date
     * @param until the end date
     * @return a list of {@code StockQuote} objects
     * @throws StockServiceException
     */
    public final List<StockQuote> getQuote(String symbol, Calendar from, Calendar until) throws StockServiceException {
        List<StockQuote> stockQuotes;
        try {
            Stock stock = YahooFinance.get(symbol);
            List<HistoricalQuote> historicalQuoteList = stock.getHistory(from,
                    until,
                    Interval.DAILY);
            stockQuotes = quoteListAdapter(historicalQuoteList);
        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }
        if (stockQuotes == null)
            throw new StockServiceException("No instance of " + symbol + " found in the selected range");

        return stockQuotes;
    }

    /**
     * Returns a list of {@code StockQuote} objects for an interval
     * The Yahoo Finance API only allows for DAILY, WEEKLY, OR MONTHLY. All other {@code IntervalEnum} enums
     * will default to DAILY.
     *
     * @param symbol the stock symbol
     * @param from the start date
     * @param until the end date
     * @param interval the interval per day to return a stock quote
     * @return a list of {@code StockQuote} objects
     * @throws StockServiceException
     */
    public final List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) throws StockServiceException {
        List<StockQuote> stockQuotes;
        yahoofinance.histquotes.Interval quoteInterval = intervalAdapter(interval);
        try {
            Stock stock = YahooFinance.get(symbol);
            List<HistoricalQuote> historicalQuoteList = stock.getHistory(from,
                    until,
                    quoteInterval);
            stockQuotes = quoteListAdapter(historicalQuoteList);
        } catch (IOException e) {
            throw new StockServiceException(e.getMessage(), e);
        }
        if (stockQuotes == null)
            throw new StockServiceException("No instance of " + symbol + " found in the selected range");

        return stockQuotes;
    }

    /**
     * Method to convert a {@code HistoricalQuote} list to a {@code StockQuote} list
     * @param quotes {@code HistoricalQuote} list to be converted
     * @return a list of {@code StockQuote} objects
     */
    private final List<StockQuote> quoteListAdapter(List<HistoricalQuote> quotes) {
        List<StockQuote> stockQuotes = new ArrayList<>();
        for (HistoricalQuote quote : quotes) {
            stockQuotes.add(new StockQuote(quote.getAdjClose(), new DateTime(quote.getDate()),quote.getSymbol()));
        }
        return stockQuotes;
    }

    /**
     * Method to convert an {@code IntervalEnum} to a {@code yahoofinance.histquotes.Interval}
     * enumeration
     * @param interval the interval to be converted
     * @return the Yahoo Finance enumeration that matches the {@code IntervalEnum}
     */
    private static final yahoofinance.histquotes.Interval intervalAdapter(IntervalEnum interval) {
        switch(interval.amount()) {
            case (1): return DAILY;
            case (168): return yahoofinance.histquotes.Interval.WEEKLY;
            case (672): return yahoofinance.histquotes.Interval.MONTHLY;
            default: return DAILY;
        }
    }

}