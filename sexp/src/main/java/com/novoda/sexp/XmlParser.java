package com.novoda.sexp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XmlParser {

    public void parse(String xml, XMLReader xmlReader) {
        parse(new ByteArrayInputStream(xml.getBytes()), xmlReader);
    }

    public void parse(InputStream xml, XMLReader xmlReader) {
        try {
            InputSource input = new InputSource(xml);
            xmlReader.parse(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

}
