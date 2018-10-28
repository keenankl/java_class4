package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.ServiceType;
import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * Factory class for stock service
 */
@Immutable
public class ServiceFactory {

    /**
     * Private constructor
     */
    private ServiceFactory() {
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
            } else if (type.equals(ServiceType.WEB)) {
                return new WebStockService();
            } else {
                throw new StockServiceException("Error: Invalid ServiceType");
            }
    }

    /**
     * @return a {@code DatabasePersonService} instance as a reference to a {@code PersonService} interface
     */
    public static final PersonService getPersonServiceInstance() {
        return new DatabasePersonService();
    }
}

