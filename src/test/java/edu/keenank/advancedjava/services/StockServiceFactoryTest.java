package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.ServiceType;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
/**
 * StockServiceFactoryTest class
 */
@Immutable
public class StockServiceFactoryTest {

    /**
     * Tests that the return value is an instance of StockService
     */
    @Test
    public void testBasicStockService() throws StockServiceException {
        assertTrue("The value returned from createStockService is not an instance of StockService interface",
                StockServiceFactory.getInstance(ServiceType.BASIC) instanceof StockService);
    }

}
