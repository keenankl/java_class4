package edu.keenank.advancedjava.servlet;


import edu.keenank.advancedjava.ServiceType;
import edu.keenank.advancedjava.model.StockSearch;
import edu.keenank.advancedjava.services.StockServiceException;
import org.junit.Assert;
import org.junit.Before;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

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

    private static WebDriver driver;
    WebElement element;

    public static void openBrowser() throws Exception {
        System.setProperty("webdriver.gecko.driver", "C:\\\\Users\\nwarr\\Downloads\\geckodriver-v0.21.0-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
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

    public final void testWebServicePositive() {
        driver.get("http://localhost:8080/StockSearchWebApp/index.jsp");
        driver.findElement(By.xpath("//a[@href='stockquote.jsp']")).click();
        driver.findElement(By.name("symbol")).sendKeys("AAPL");
        driver.findElement(By.name("from")).sendKeys("2018-011-01 00:00:00");
        driver.findElement(By.name("until")).sendKeys("2016-11-05 00:00:00");
        driver.findElement(By.xpath("//input[@value='WEB']")).click();
        driver.findElement(By.xpath("//input[@value='OK']")).click();
        element = driver.findElement(By.xpath("//*[text()='Stock Quote Search Result']"));
        Assert.assertNotNull(element);
    }

    public static void closeBrowser(){
        driver.quit();
    }

}