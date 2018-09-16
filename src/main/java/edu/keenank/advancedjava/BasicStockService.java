package edu.keenank.advancedjava;

/**
 * Implements the interface to define methods for getting stock quotes
 *
 * */
public class BasicStockService implements StockService {
    /**
     * Gets StockQuote  instance for a symbol
     * @param symbol stock symbol
     * @return a new StockQuote
     */
    public StockQuote getQuote(String symbol) {
        return new StockQuote(symbol);
    }
}
