package edu.keenank.advancedjava.util;

import edu.keenank.advancedjava.model.XMLStockQuoteList;
import edu.keenank.advancedjava.utl.XMLException;
import edu.keenank.advancedjava.utl.XMLUtils;
import org.junit.Test;

import javax.xml.transform.sax.SAXResult;

import static org.junit.Assert.assertTrue;

/**
 * Unit tests for XML utils.
 */
public final class XMLUtilsTest {

    @Test
    public final void testUnmarshalPositive() throws XMLException {
        XMLStockQuoteList quoteList = XMLUtils.unmarshal(XMLUtils.xmlFilePath);
        assertTrue("unmarshal() return value is not an XMLStockQuoteList instance", quoteList instanceof XMLStockQuoteList);
    }

    @Test(expected=XMLException.class)
    public final void testUnmarshalNegative() throws XMLException {
        XMLStockQuoteList quoteList = XMLUtils.unmarshal("invalid path");
    }

    @Test
    public final void testMarshalPositive() throws XMLException {
        XMLStockQuoteList quoteList = XMLUtils.unmarshal(XMLUtils.xmlFilePath);
        SAXResult result = XMLUtils.marshal(quoteList);
        assertTrue("marshal() return value is not an SAXResult instance", result instanceof SAXResult);
    }

    @Test(expected=IllegalArgumentException.class)
    public final void testMarshallNegative() throws XMLException {
        XMLStockQuoteList quoteList = null;
        XMLUtils.marshal(quoteList);
    }
}
