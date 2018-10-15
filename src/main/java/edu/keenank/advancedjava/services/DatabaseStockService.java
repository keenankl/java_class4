package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.IntervalEnum;
import edu.keenank.advancedjava.model.StockQuote;
import edu.keenank.advancedjava.utl.DatabaseConnectionException;
import edu.keenank.advancedjava.utl.DatabaseUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * An implementation of the StockService interface that gets
 * stock data from a database.
 */
public class DatabaseStockService implements StockService {


    /**
     * Return the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. AAPL for APPLE
     * @return a  <CODE>BigDecimal</CODE> instance
     * @throws StockServiceException if using the service generates an exception.
     *                               If this happens, trying the service may work, depending on the actual cause of the
     *                               error.
     */
    public final StockQuote getQuote(String symbol) throws StockServiceException {
        List<StockQuote> stockQuotes;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select id from stock_symbols where symbol = '" + symbol + "'";
            ResultSet resultSet = statement.executeQuery(queryString);
            resultSet.next();
            queryString = "select * from quotes where symbol_id = '" + resultSet.getInt("id") + "'";
            resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<StockQuote>(resultSet.getFetchSize());
            while(resultSet.next()) {
                BigDecimal price = resultSet.getBigDecimal("price");
                Timestamp timestamp = resultSet.getTimestamp("time");
                DateTime time = new DateTime(timestamp);
                stockQuotes.add(new StockQuote(price, time, symbol));
            }
        } catch (DatabaseConnectionException | SQLException e) {
            throw new StockServiceException(e.getMessage(), e);
        }
        if (stockQuotes.isEmpty()) {
            throw new StockServiceException("No instances of " + symbol + " found in the selected range");
        }
        return stockQuotes.get(stockQuotes.size() - 1);
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
        List<StockQuote> stockQuotes;
        try {
            Connection connection = DatabaseUtils.getConnection();
            Statement statement = connection.createStatement();
            String queryString = "select id from stock_symbols where symbol = '" + symbol + "'";
            ResultSet resultSet = statement.executeQuery(queryString);
            resultSet.next();
            queryString = "select * from quotes where symbol_id = '" + resultSet.getInt("id") + "'" +
                    "AND time between '" + from.toString() + "' and '" +
                    until.toString() + "'";

            resultSet = statement.executeQuery(queryString);
            stockQuotes = new ArrayList<>(resultSet.getFetchSize());
            while(resultSet.next()) {
                Timestamp timeStamp = resultSet.getTimestamp("time");
                DateTime time = new DateTime(timeStamp.getTime());
                BigDecimal price = resultSet.getBigDecimal("price");
                stockQuotes.add(new StockQuote(price, time, symbol));
            }
        } catch (DatabaseConnectionException | SQLException exception) {
            throw new StockServiceException(exception.getMessage(), exception);
        }
        return stockQuotes;
    }

    /**
     * Get a historical list of stock quotes for the provide symbol
     *
     * @param symbol the stock symbol to search for
     * @param from   the date of the first stock quote
     * @param until  the date of the last stock quote
     * @param interval the time interval for the period
     * @return null
     * @throws   StockServiceException if using the service generates an exception.
     * If this happens, trying the service may work, depending on the actual cause of the
     * error.
     */
    @Override
    public final List<StockQuote> getQuote(String symbol, Calendar from, Calendar until, IntervalEnum interval) {
        return null;
    }
}
