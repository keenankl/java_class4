package edu.keenank.advancedjava.servlet;


import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.model.StockSearch;
import edu.keenank.advancedjava.services.StockServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * StockSearchServletTest
 */
public class StockSearchServletTest {

    private static HttpServletRequest request;
    private static HttpServletResponse response;
    private static PrintWriter writer;
    private static StockSearchServlet servlet;
    private static final String SYMBOL_PARAMETER_KEY = "symbol";
    private static final String FROM_PARAMETER_KEY = "from";
    private static final String UNTIL_PARAMETER_KEY = "until";
    private static final String INTERVAL_PARAMETER_KEY = "interval";
    private static final String SERVICETYPE_PARAMETER_KEY = "serviceType";

    @Before
    public final void setUp() throws ParseException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        when(request.getParameter("symbol"))
                .thenReturn("AAPL");

        when(request.getParameter("from"))
                .thenReturn("2018-01-01 00:00:00");

        when(request.getParameter("until"))
                .thenReturn("2018-05-01 00:00:00");

        when(request.getParameter("interval"))
                .thenReturn("DAY");

        when(request.getParameter("serviceType"))
                .thenReturn("WEB");

        String symbol = request.getParameter(SYMBOL_PARAMETER_KEY);
        String from = request.getParameter(FROM_PARAMETER_KEY);
        String until = request.getParameter(UNTIL_PARAMETER_KEY);
        String interval = request.getParameter(INTERVAL_PARAMETER_KEY);
        String serviceType = request.getParameter(SERVICETYPE_PARAMETER_KEY);

        HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        final ServletContext servletContext = Mockito.mock(ServletContext.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(servletContext.getRequestDispatcher("/stocksearchResults.jsp")).thenReturn(dispatcher);
        servlet = new StockSearchServlet() {
            public ServletContext getServletContext() {
                return servletContext;
            }
        };

        StockSearch search = new StockSearch(symbol, from, until, interval);
        try {
            switch (serviceType) {
                case ("BASIC"):
                    search.processData(ServiceType.BASIC);
                    break;
                case ("DATABASE"):
                    search.processData(ServiceType.DATABASE);
                    break;
                case ("WEB"):
                    search.processData(ServiceType.WEB);
                    break;
                default:
                    search.processData(ServiceType.WEB);
            }
        } catch (StockServiceException e) {
            throw new RuntimeException(e.getMessage());
        }
        session.setAttribute("search", search);
    }

    @Test(expected = NullPointerException.class)
    public final void testDoPostPositive() throws ServletException, IOException {
        servlet.doPost(null, null);
    }

    @Test
    public final void testDoPostNegative() throws ServletException, IOException {
        boolean throwsException = false;
        try {
            servlet.doPost(request, response);
        } catch (Exception e) {
            throwsException = true;
        }
        assertFalse("doPost throws an exception", throwsException);
    }

}