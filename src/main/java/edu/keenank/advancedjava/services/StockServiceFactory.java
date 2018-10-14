package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.ServiceType;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Factory class for stock service
 */
@Immutable
public class StockServiceFactory {

    /**
     * Private constructor
     */
    private StockServiceFactory() {
    }

    /**
     * Constructs a new StockService
     * @return an object using the StockService interface
     */
    public static final StockService getStockServiceInstance (ServiceType type) throws StockServiceException {
            if (type.equals(ServiceType.BASIC)) {
                return new BasicStockService();
            } else if (type.equals(ServiceType.DATABASE)) {
                return new DatabaseStockService();
            } else {
                throw new StockServiceException("Error: Invalid ServiceType");
            }
    }
}

