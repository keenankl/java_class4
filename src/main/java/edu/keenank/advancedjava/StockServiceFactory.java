package edu.keenank.advancedjava;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Factory class for stock service
 */
@Immutable
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
