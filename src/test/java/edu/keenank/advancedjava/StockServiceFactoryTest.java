package edu.keenank.advancedjava;

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
    public void testBasicStockService() {
        assertTrue("The value returned from createStockService is not an instance of StockService interface",
                StockServiceFactory.getStockService() instanceof StockService);
    }

}
