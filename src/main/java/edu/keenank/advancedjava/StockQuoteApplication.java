package edu.keenank.advancedjava;

import edu.keenank.advancedjava.services.StockService;
import edu.keenank.advancedjava.services.StockServiceException;
import edu.keenank.advancedjava.services.StockServiceFactory;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Application class with a main method to create StockService instances
 */
@Immutable
public class StockQuoteApplication {
    private StockService basicStockService;

    /**
     * Constructs a new {@code BasicStockQuote} instance
     * @param basicStockService used to get actual stock data dummy interface
     */
    public StockQuoteApplication(StockService basicStockService) {
        this.basicStockService = basicStockService;
    }

    /**
     * @param args user input that contains elements:
     * <code>String</code>  stock symbol, start date, end date, and interval
     * Prints out the results
     */
    public static void main(String[] args) throws ParseException, StockServiceException {
        StockService basicStockService = StockServiceFactory.getStockServiceInstance(ServiceType.BASIC);
        StockService databaseStockService = StockServiceFactory.getStockServiceInstance(ServiceType.DATABASE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        System.out.println(basicStockService.getQuote(args[0]));
        System.out.println(databaseStockService.getQuote(args[0]));

        Calendar from = Calendar.getInstance();
        from.setTime(sdf.parse(args[1]));

        Calendar until = Calendar.getInstance();
        until.setTime(sdf.parse(args[2]));

        System.out.println(basicStockService.getQuote(args[0], from, until));
        System.out.println(databaseStockService.getQuote(args[0], from, until));

        IntervalEnum interval = IntervalEnum.valueOf(args[3]);
        System.out.println(basicStockService.getQuote(args[0], from, until, interval));
        System.out.println(databaseStockService.getQuote(args[0], from, until, interval));
    }

}