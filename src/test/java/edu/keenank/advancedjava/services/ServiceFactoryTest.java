package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.ServiceType;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ServiceFactoryTest class
 */
@Immutable
public class ServiceFactoryTest {

    @Test
    public void testBasicStockService() throws StockServiceException {
        assertTrue("The value returned from getInstance does not match StockService interface",
                ServiceFactory.getStockServiceInstance(ServiceType.BASIC) instanceof StockService);
    }

    @Test
    public final void testCreateBasicStockServiceNegative() throws StockServiceException {
        assertFalse("The value returned from getInstance matches Calendar class",
                ServiceFactory.getStockServiceInstance(ServiceType.BASIC) instanceof Calendar);
    }

    @Test
    public final void testCreateDatabaseStockServicePositive() throws StockServiceException {
        assertTrue("The value returned from getInstance does not match StockService interface",
                ServiceFactory.getStockServiceInstance(ServiceType.DATABASE) instanceof StockService);
    }

    @Test
    public final void testCreateDatabaseStockServiceNegative() throws StockServiceException {
        assertFalse("The value returned from getInstance matches of Calendar class",
                ServiceFactory.getStockServiceInstance(ServiceType.DATABASE) instanceof Calendar);
    }

}
