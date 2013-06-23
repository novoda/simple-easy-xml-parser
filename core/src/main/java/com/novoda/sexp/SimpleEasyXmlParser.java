package com.novoda.sexp;

import android.sax.RootElement;

import com.novoda.sexp.finder.ElementFinderFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class SimpleEasyXmlParser {

    public static ElementFinderFactory getElementCreatorFactory() {
        return new ElementFinderFactory();
    }

    public static void parse(String xml, String encoding, Instigator instigator) throws UnsupportedEncodingException {
        parse(new ByteArrayInputStream(xml.getBytes(encoding)), instigator);
    }

    public static void parse(String xml, Instigator instigator) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator);
    }

    public static void parse(InputStream xml, Instigator instigator) {
        String rootTag = instigator.getRootTag();
        RootElement rootElement = new RootElement(rootTag);
        rootElement.setEndElementListener(instigator);
        instigator.create(rootElement);
        XmlParser xmlParser = new XmlParser();
        xmlParser.parse(xml, rootElement.getContentHandler());
    }

}
