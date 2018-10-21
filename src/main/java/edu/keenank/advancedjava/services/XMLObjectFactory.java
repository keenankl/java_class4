package edu.keenank.advancedjava.services;

import edu.keenank.advancedjava.model.XMLDomainObject;
import edu.keenank.advancedjava.model.XMLStockQuote;
import edu.keenank.advancedjava.model.XMLStockQuoteList;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * Factory class for XML Objects
 */
@XmlRegistry
public class XMLObjectFactory implements XMLDomainObject {

    private final static QName _Stocks_QNAME = new QName("", "stocks");

    /**
     * Create a new XMLObjectFactory
     *
     */
    public XMLObjectFactory() {
    }

    /**
     * Create an instance of {@link XMLStockQuoteList }
     *
     */
    public XMLStockQuoteList createXMLStockQuoteList() {
        return new XMLStockQuoteList();
    }

    /**
     * Create an instance of {@link XMLStockQuote }
     *
     */
    public XMLStockQuote createXMLStockQuote() {
        return new XMLStockQuote();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLStockQuoteList }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "stocks")
    public JAXBElement<XMLStockQuoteList> createStocks(XMLStockQuoteList value) {
        return new JAXBElement<XMLStockQuoteList>(_Stocks_QNAME, XMLStockQuoteList.class, null, value);
    }

}
