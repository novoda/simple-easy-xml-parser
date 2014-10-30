package com.novoda.sexp;

import com.novoda.sax.RootElement;
import com.novoda.sexp.finder.ElementFinderFactory;

import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SimpleEasyXmlParser {

    public static ElementFinderFactory getElementFinderFactory() {
        return new ElementFinderFactory();
    }

    public static void parse(String xml, String encoding, Instigator instigator) throws UnsupportedEncodingException {
        parse(new ByteArrayInputStream(xml.getBytes(encoding)), instigator, getDefaultSEXPXMLReader());
    }

    public static void  parse(String xml, Instigator instigator) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator, getDefaultSEXPXMLReader());
    }

    public static void parse(String xml, Instigator instigator, XMLReader xmlReader) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator, xmlReader);
    }

    public static void parse(InputStream xml, Instigator instigator, XMLReader xmlReader) {
        RootTag rootTag = instigator.getRootTag();
        RootElement rootElement = new RootElement(rootTag.getNamespace(), rootTag.getTag());
        rootElement.setEndElementListener(instigator);
        instigator.create(rootElement);
        xmlReader.setContentHandler(rootElement.getContentHandler());
        XmlParser xmlParser = new XmlParser();
        xmlParser.parse(xml, xmlReader);
    }

    private static XMLReader getDefaultSEXPXMLReader() {
        try {
            return new XMLReaderBuilder().allowNamespaceProcessing(true).build();
        } catch (XMLReaderBuilder.XMLReaderCreationException e) {
            throw new RuntimeException(e);
        }
    }

}
