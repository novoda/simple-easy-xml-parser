package com.novoda.sexp;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;

public class XMLReaderBuilder {

    private static final String FEATURE_NAMESPACE = "http://xml.org/sax/features/namespaces";
    private static final String FEATURE_NAMESPACE_PREFIX = "http://xml.org/sax/features/namespace-prefixes";

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

    public XMLReaderBuilder setNamespace(boolean value) throws XMLReaderCreationException {
        withFeature(FEATURE_NAMESPACE, value);
        return this;
    }

    public XMLReaderBuilder setAttributeNamespace(boolean value) throws XMLReaderCreationException {
        withFeature(FEATURE_NAMESPACE_PREFIX, value);
        return this;
    }

    public XMLReaderBuilder setNamespaceAware(boolean value) {
        saxParserFactory.setNamespaceAware(value);
        return this;
    }

    public XMLReaderBuilder setXIncludeAware(boolean value) {
        saxParserFactory.setXIncludeAware(value);
        return this;
    }

    public XMLReaderBuilder setSchema(Schema schema) {
        saxParserFactory.setSchema(schema);
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
