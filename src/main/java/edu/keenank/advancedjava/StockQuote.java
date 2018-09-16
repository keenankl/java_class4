package edu.keenank.advancedjava;
/**
 * Container class for the stock quote
 */
public class StockQuote {
    private String symbol;
    /**
     * Constructs a new StockQuote
     * @param symbol stock symbol
     */

    public StockQuote(String symbol) {
        this.symbol = symbol;
    }
    /**
     * @return the stock symbol
     */
    public String getSymbol() {
        return symbol;
    }

}
