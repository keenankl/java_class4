package edu.keenank.advancedjava.servlet;

import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.model.StockSearch;
import edu.keenank.advancedjava.services.StockServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

/**
 * Servlet for the stock quote search web application
 *
 */
public class StockSearchServlet extends HttpServlet {

    private static final String SYMBOL_PARAMETER_KEY = "symbol";
    private static final String FROM_PARAMETER_KEY = "from";
    private static final String UNTIL_PARAMETER_KEY = "until";
    private static final String INTERVAL_PARAMETER_KEY = "interval";
    private static final String SERVICETYPE_PARAMETER_KEY = "serviceType";

    /**
     * Requests access to form and generates a response
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String symbol = request.getParameter(SYMBOL_PARAMETER_KEY);
        String from = request.getParameter(FROM_PARAMETER_KEY);
        String until = request.getParameter(UNTIL_PARAMETER_KEY);
        String interval = request.getParameter(INTERVAL_PARAMETER_KEY);
        String serviceType = request.getParameter(SERVICETYPE_PARAMETER_KEY);

        HttpSession session = request.getSession();

        StockSearch search = null;
        try {
            search = new StockSearch(symbol, from, until, interval);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher("/stocksearchResults.jsp");
        dispatcher.forward(request, response);
    }
}