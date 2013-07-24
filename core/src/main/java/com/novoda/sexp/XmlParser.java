package com.novoda.sexp;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XmlParser {

    public void parse(String xml, ContentHandler contentHandler) {
        parse(new ByteArrayInputStream(xml.getBytes()), contentHandler);
    }

    public void parse(InputStream xml, ContentHandler contentHandler) {
        try {
            XMLReader reader = getXmlReader();
            reader.setContentHandler(contentHandler);
            InputSource input = new InputSource(xml);
            reader.parse(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private XMLReader getXmlReader() throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setFeature("http://xml.org/sax/features/namespaces", true);
        saxParserFactory.setFeature("http://xml.org/sax/features/namespace-prefixes", true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        return saxParser.getXMLReader();
    }
}
