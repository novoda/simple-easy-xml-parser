package com.novoda.sexp;

import com.novoda.sax.RootElement;
import com.novoda.sexp.finder.ElementFinderFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.xml.sax.XMLReader;

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

    public static void parse(InputStream xml, Instigator instigator) {
        parse(xml, instigator, getDefaultSEXPXMLReader());
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

    public static <T> T parse(String xml, String encoding, ElementFinderInstigator<T> instigator) throws UnsupportedEncodingException {
        return parse(new ByteArrayInputStream(xml.getBytes(encoding)), instigator, getDefaultSEXPXMLReader());
    }

    public static <T> T parse(String xml, ElementFinderInstigator<T> instigator) {
        return parse(new ByteArrayInputStream(xml.getBytes()), instigator, getDefaultSEXPXMLReader());
    }

    public static <T> T parse(InputStream xml, ElementFinderInstigator<T> instigator) {
        return parse(xml, instigator, getDefaultSEXPXMLReader());
    }

    public static <T> T parse(String xml, ElementFinderInstigator<T> instigator, XMLReader xmlReader) {
        return parse(new ByteArrayInputStream(xml.getBytes()), instigator, xmlReader);
    }

    public static <T> T parse(InputStream xml, final ElementFinderInstigator<T> instigator, XMLReader xmlReader) {
        final CountDownLatch latch = new CountDownLatch(1);
        Instigator latchedInstigator = new Instigator() {
            @Override
            public RootTag getRootTag() {
                return instigator.getRootTag();
            }

            @Override
            public void create(RootElement rootElement) {
                instigator.create(rootElement);
            }

            @Override
            public void end() {
                latch.countDown();
            }
        };

        RootTag rootTag = instigator.getRootTag();
        RootElement rootElement = new RootElement(rootTag.getNamespace(), rootTag.getTag());
        rootElement.setEndElementListener(latchedInstigator);
        instigator.create(rootElement);
        xmlReader.setContentHandler(rootElement.getContentHandler());
        XmlParser xmlParser = new XmlParser();
        xmlParser.parse(xml, xmlReader);

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        return instigator.getResultOrThrow();
    }

    private static XMLReader getDefaultSEXPXMLReader() {
        try {
            return new XMLReaderBuilder().allowNamespaceProcessing(true).build();
        } catch (XMLReaderBuilder.XMLReaderCreationException e) {
            throw new RuntimeException(e);
        }
    }

}
