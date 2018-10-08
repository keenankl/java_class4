package edu.keenank.advancedjava;

import edu.keenank.advancedjava.services.StockService;
import edu.keenank.advancedjava.services.StockServiceException;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

/**
 * StockQuoteApplicationTest class
 */
@Immutable
public class StockQuoteApplicationTest {

    private StockQuoteApplication stockQuoteApplication;
    private StockService stockServiceMock;

    @Before
    public void setUp() {
        stockServiceMock = mock(StockService.class);
    }

    @Test
    public void testValidConstruction() {
        stockQuoteApplication = new StockQuoteApplication(stockServiceMock);
        assertNotNull("Basic construction works");
    }

    /**
     * Tests for a NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testMainNegative() throws ParseException, StockServiceException {
        StockQuoteApplication.main(null);
    }

}