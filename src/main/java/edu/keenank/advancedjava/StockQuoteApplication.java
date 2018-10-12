package edu.keenank.advancedjava;

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

    public StockQuoteApplication(StockService basicStockService) {
        this.basicStockService = basicStockService;
    }

    /**
     * @param args user input that contains elements:
     * <code>String</code>  stock symbol, start date, end date, and interval
     * Prints out the results
     */
    public static void main(String[] args) throws ParseException {
        StockService basicStockService = StockServiceFactory.getStockService();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

        System.out.println(basicStockService.getQuote(args[0]));

        Calendar from = Calendar.getInstance();
        from.setTime(sdf.parse(args[1]));

        Calendar until = Calendar.getInstance();
        until.setTime(sdf.parse(args[2]));

        System.out.println(basicStockService.getQuote(args[0], from, until));

        IntervalEnum interval = IntervalEnum.valueOf(args[3]);
        System.out.println(basicStockService.getQuote(args[0], from, until, interval));
    }

}
