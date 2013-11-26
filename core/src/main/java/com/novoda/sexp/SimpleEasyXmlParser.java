package com.novoda.sexp;

import com.novoda.sax.RootElement;
import com.novoda.sexp.finder.ElementFinderFactory;

import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

public class SimpleEasyXmlParser {

    public static ElementFinderFactory getElementFinderFactory() {
        return new ElementFinderFactory();
    }

    public static void parse(String xml, String encoding, Instigator instigator) throws UnsupportedEncodingException {
        parse(new ByteArrayInputStream(xml.getBytes(encoding)), instigator, getDefaultSEXPXMLReader());
    }

    public static void parse(String xml, Instigator instigator) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator, getDefaultSEXPXMLReader());
    }

    public static void parse(String xml, Instigator instigator, XMLReader xmlReader) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator, xmlReader);
    }

    private static void parse(ByteArrayInputStream xml, Instigator instigator, XMLReader xmlReader) {
        String rootTag = instigator.getRootTag();
        RootElement rootElement = new RootElement(rootTag);
        rootElement.setEndElementListener(instigator);
        instigator.create(rootElement);
        XmlParser xmlParser = new XmlParser();
        xmlParser.parse(xml, rootElement.getContentHandler(), xmlReader);
    }

    private static XMLReader getDefaultSEXPXMLReader() {
        try {
            return SEXPXmlReaderFactory.getDefault();
        } catch (XMLReaderBuilder.XMLReaderCreationException e) {
            throw new RuntimeException(e);
        }
    }

}
