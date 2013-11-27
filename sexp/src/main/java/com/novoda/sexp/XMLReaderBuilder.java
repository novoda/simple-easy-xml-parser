package com.novoda.sexp;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XMLReaderBuilder {

    private final SAXParserFactory saxParserFactory;

    public XMLReaderBuilder() {
        this(SAXParserFactory.newInstance());
    }

    XMLReaderBuilder(SAXParserFactory saxParserFactory) {
        this.saxParserFactory = saxParserFactory;
    }

    public XMLReaderBuilder withFeature(String name, boolean value) throws XMLReaderCreationException {
        try {
            saxParserFactory.setFeature(name, value);
        } catch (ParserConfigurationException e) {
            throw new XMLReaderCreationException(e);
        } catch (SAXNotRecognizedException e) {
            throw new XMLReaderCreationException(e);
        } catch (SAXNotSupportedException e) {
            throw new XMLReaderCreationException(e);
        }
        return this;
    }

    public XMLReader build() throws XMLReaderCreationException {
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            return saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            throw new XMLReaderCreationException(e);
        } catch (SAXException e) {
            throw new XMLReaderCreationException(e);
        }
    }

    public static class XMLReaderCreationException extends Exception {
        public XMLReaderCreationException(Exception e) {
            super(e);
        }
    }

}
