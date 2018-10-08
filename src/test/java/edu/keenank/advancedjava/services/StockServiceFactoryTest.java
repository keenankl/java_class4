package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.ServiceType;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * StockServiceFactoryTest class
 */
@Immutable
public class StockServiceFactoryTest {

    /**
     * Tests that the return value is an instance of StockService
     * @throws StockServiceException
     */
    @Test
    public void testBasicStockService() throws StockServiceException {
        assertTrue("The value returned from getInstance does not match StockService interface",
                StockServiceFactory.getInstance(ServiceType.BASIC) instanceof StockService);
    }

    /**
     * Verifies that the return value is not an instance of the specified class
     * @throws StockServiceException
     */
    @Test
    public final void testCreateBasicStockServiceNegative() throws StockServiceException {
        assertFalse("The value returned from getInstance matches Calendar class",
                StockServiceFactory.getInstance(ServiceType.BASIC) instanceof Calendar);
    }

    /**
     * Verifies that the return value is an instance of the specified class
     * @throws StockServiceException
     */
    @Test
    public final void testCreateDatabaseStockServicePositive() throws StockServiceException {
        assertTrue("The value returned from getInstance does not match StockService interface",
                StockServiceFactory.getInstance(ServiceType.DATABASE) instanceof StockService);
    }

    /**
     * Verifies that the return value is not an instance of the specified class
     * @throws StockServiceException
     */
    @Test
    public final void testCreateDatabaseStockServiceNegative() throws StockServiceException {
        assertFalse("The value returned from getInstance matches of Calendar class",
                StockServiceFactory.getInstance(ServiceType.DATABASE) instanceof Calendar);
    }

}
