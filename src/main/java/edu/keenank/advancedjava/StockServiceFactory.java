package edu.keenank.advancedjava;
/**
 * Factory class for stock service
 */
public class StockServiceFactory {
    /**
     * Constructs a new StockService
     * @return an object using the StockService interface
     */
    public static StockService getStockService()
    {
        return new BasicStockService();

    }
}
