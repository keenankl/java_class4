package edu.keenank.advancedjava.model;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.services.ServiceFactory;
import edu.keenank.advancedjava.services.StockService;
import edu.keenank.advancedjava.services.StockServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Container class for stock searches
 */
public class StockSearch {

    private String symbol;
    private Calendar from;
    private Calendar until;
    private IntervalEnum interval;
    private String quoteStr;

    /**
     * constructs a new {@code StockSearch} instance
     */
    public StockSearch() {}

    /**
     * constructs a new {@code StockSearch} instance
     *
     * @param symbol the stock symbol entered
     * @param from the starting date of symbol being queried
     * @param until the end date of the symbol being queried
     * @param interval the interval at which to retrieve the stock quotes between the supplied dates
     */
    public StockSearch(String symbol, String from, String until, String interval) throws ParseException {
        setSymbol(symbol);
        setFrom(from);
        setUntil(until);
        setInterval(interval);
    }

    /**
     * Returns a String representation of the symbol
     * @return a stock symbol
     */
    public final String getSymbol() {
        return symbol;
    }

    /**
     * Sets a String representation of the stock symbol
     * @param symbol a stock symbol
     */
    public final void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns a Calendar representation of the start date
     * @return the starting date of the stock quote
     */
    public final String getFrom() {
        return from.toString();
    }

    /**
     * Sets a Calendar representation of the start date
     * @param from
     */
    public final void setFrom(String from) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
        cal.setTime(sdf.parse(from));
        this.from = cal;
    }

    /**
     * Returns a Calendar representation of the end date
     * @return the end date of the stock quote
     */
    public final String getUntil() {
        return until.toString();
    }

    /**
     * Sets a Calendar representation of the end date
     * @param until
     */
    public final void setUntil(String until) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss", Locale.ENGLISH);
        cal.setTime(sdf.parse(until));
        this.until = cal;
    }

    /**
     * Returns the interval
     * @return an interval of stock data
     */
    public final String getInterval() {
        return interval.toString();
    }

    /**
     * Sets the interval for the stock dara
     * @param interval
     */
    public final void setInterval(String interval) {
        String intervalStr = interval.toUpperCase();
        switch (intervalStr) {
            case("HOUR"):
                this.interval = IntervalEnum.HOURLY;
                break;
            case("DAY"):
                this.interval = IntervalEnum.DAILY;
                break;
            case("WEEK"):
                this.interval = IntervalEnum.WEEKLY;
                break;
            default:
                this.interval = IntervalEnum.DAILY;
        }
    }

    /**
     * Returns the quote as a string
     * @return a quote
     */
    public final String getQuoteStr() {
        return quoteStr;
    }

    /**
     * RSets the quote as a string
     * @return a quote
     */
    public final void setQuoteStr(String quoteStr) {
        this.quoteStr = quoteStr;
    }

    /**
     * Generates a String of the quotes
     * @throws StockServiceException
     */
    public void processData(ServiceType type) throws StockServiceException {
        if (this.validateData()) {
            StockService service = ServiceFactory.getStockServiceInstance(type);
            List<StockQuote> quoteList = service.getQuote(symbol, from, until, interval);
            StringBuilder builder = new StringBuilder();
            for (StockQuote q : quoteList) {
                builder.append(q.toString());
            }
            this.quoteStr = builder.toString();
        } else {
            throw new IllegalStateException("Not all fields have been initialized");
        }
    }

    /**
     * Verifies that the quotes are valid
     * @return true
     */
    public boolean validateData() {
        if ((this.symbol == null) || (this.from == null) || (this.until == null) || this.interval == null)
            return false;
        return true;
    }
}