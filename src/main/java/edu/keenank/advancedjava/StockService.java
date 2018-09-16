package edu.keenank.advancedjava;

/**
 *  This interface describes an API for getting stock data.
 */
public interface StockService {
    /**
     * Return
     * the current price for a share of stock  for the given symbol
     *
     * @param symbol the stock symbol of the company you want a quote for.
     *               e.g. AAPL for APPLE
     * @return a  <CODE>StockQuote</CODE> instance
     */
    StockQuote getQuote(String symbol);
}