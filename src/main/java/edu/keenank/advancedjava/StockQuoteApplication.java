package edu.keenank.advancedjava;

import edu.keenank.advancedjava.model.XMLStockQuoteList;
import edu.keenank.advancedjava.services.StockService;
import edu.keenank.advancedjava.services.StockServiceException;
import edu.keenank.advancedjava.services.StockServiceFactory;
import jdk.nashorn.internal.ir.annotations.Immutable;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Application class with a main method to create StockService instances
 */
@Immutable
public class StockQuoteApplication {
    private StockService basicStockService;

    private static String xmlInstance ="<stocks>\n" +
            "    <stock symbol=\"VNET\" price=\"110.10\" time=\"2015-02-10 00:00:01\"/>\n" +
            "    <stock symbol=\"AGTK\" price=\"120.10\" time=\"2015-02-10 00:00:01\"/>\n" +
            "</stocks>";

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
    public static void main(String[] args) throws ParseException, StockServiceException, JAXBException {
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

        JAXBContext jaxbContext = JAXBContext.newInstance(XMLStockQuoteList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        XMLStockQuoteList quotes = (XMLStockQuoteList) unmarshaller.unmarshal(new StringReader(xmlInstance));
        System.out.println(quotes.toString());

        JAXBContext context = JAXBContext.newInstance(XMLStockQuoteList.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(quotes, System.out);
    }

}