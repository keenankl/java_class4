package edu.keenank.advancedjava.utl;

import edu.keenank.advancedjava.model.XMLStockQuoteList;
import org.dom4j.io.SAXContentHandler;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXResult;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

/**
 * A collection of helper methods for marshaling and unmarshaling XML instances.
 */
public final class XMLUtils {

    public static final String xmlFilePath = "src/main/resources/xml/stock_info.xml";

    /**
     * Put the provided XML String into the specified XML Domain Object using JAXB without using
     * schema validation.
     * @param xmlPath String containing a reference to the file containing XML data
     * @return XMLStockQuoteList
     * @throws XMLException
     */
    public static final XMLStockQuoteList unmarshal(String xmlPath) throws XMLException {
        XMLStockQuoteList quotes = null;
        try {
            boolean eof = false;
            StringBuilder xmlInstance = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(xmlPath));
            while (!eof) {
                String line = reader.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    xmlInstance.append(line);
                }
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(XMLStockQuoteList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            quotes = (XMLStockQuoteList) unmarshaller.unmarshal(new StringReader(xmlInstance.toString()));
        } catch (IOException | JAXBException e) {
            throw new XMLException(e.getMessage());
        }
        return quotes;
    }

    /**
     * Serializes the domainClass into an XML instance which is returned as a String.
     * @param quotes list of quotes in the format of an XML domain object
     * @return SAXResult
     * @throws XMLException
     */
    public static final SAXResult marshal(XMLStockQuoteList quotes) throws XMLException {
        SAXResult result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(XMLStockQuoteList.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            result = new SAXResult(new SAXContentHandler());
            marshaller.marshal(quotes, result);
        } catch (JAXBException e) {
            throw new XMLException(e.getMessage());
        }
        return result;
    }
}