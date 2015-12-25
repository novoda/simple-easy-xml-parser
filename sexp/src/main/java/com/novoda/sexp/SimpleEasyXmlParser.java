package com.novoda.sexp;

import com.novoda.sax.RootElement;
import com.novoda.sexp.finder.ElementFinderFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

import org.xml.sax.XMLReader;

public class SimpleEasyXmlParser {

    /**
     * @return an {@link ElementFinderFactory} this Factory is how you select which XML elements to parse as what java types
     * see it's javadoc for more
     */
    public static ElementFinderFactory getElementFinderFactory() {
        return new ElementFinderFactory();
    }

    /**
     * @param xml        the xml to parse
     * @param encoding   the encoding of your xml
     * @param instigator your fully created parser of the xml
     * @throws UnsupportedEncodingException if the encoding you passed isn't suppored
     */
    public static void parse(String xml, String encoding, Instigator instigator) throws UnsupportedEncodingException {
        parse(new ByteArrayInputStream(xml.getBytes(encoding)), instigator, getDefaultSEXPXMLReader());
    }

    /**
     * @param xml        the xml to parse
     * @param instigator your fully created parser of the xml
     */
    public static void parse(String xml, Instigator instigator) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator, getDefaultSEXPXMLReader());
    }

    /**
     * @param xml        the xml to parse
     * @param instigator your fully created parser of the xml
     */
    public static void parse(InputStream xml, Instigator instigator) {
        parse(xml, instigator, getDefaultSEXPXMLReader());
    }

    /**
     * @param xml        the xml to parse
     * @param instigator your fully created parser of the xml
     * @param xmlReader  is the interface that an XML parser's SAX2 driver must implement , using this? _bad ass_ alert
     */
    public static void parse(String xml, Instigator instigator, XMLReader xmlReader) {
        parse(new ByteArrayInputStream(xml.getBytes()), instigator, xmlReader);
    }

    /**
     * @param xml        the xml to parse
     * @param instigator your fully created parser of the xml
     * @param xmlReader  is the interface that an XML parser's SAX2 driver must implement , using this? _bad ass_ alert
     */
    public static void parse(InputStream xml, Instigator instigator, XMLReader xmlReader) {
        RootTag rootTag = instigator.getRootTag();
        RootElement rootElement = new RootElement(rootTag.getNamespace(), rootTag.getTag());
        rootElement.setEndElementListener(instigator);
        instigator.create(rootElement);
        xmlReader.setContentHandler(rootElement.getContentHandler());
        XmlParser xmlParser = new XmlParser();
        xmlParser.parse(xml, xmlReader);
    }

    /**
     * @param xml        the xml to parse
     * @param encoding   the encoding of your xml
     * @param streamer   your fully created streamer of the xml
     * @param <T>        the expected type of your parsed XML
     * @return your parsed XML as an object
     * @throws UnsupportedEncodingException if the encoding you passed isn't suppored
     */
    public static <T> T parse(String xml, String encoding, Streamer<T> streamer) throws UnsupportedEncodingException {
        return parse(new ByteArrayInputStream(xml.getBytes(encoding)), streamer, getDefaultSEXPXMLReader());
    }

    /**
     * @param xml        the xml to parse
     * @param streamer   your fully created streamer of the xml
     * @param <T>        the expected type of your parsed XML
     * @return your parsed XML as an object
     */
    public static <T> T parse(String xml, Streamer<T> streamer) {
        return parse(new ByteArrayInputStream(xml.getBytes()), streamer, getDefaultSEXPXMLReader());
    }

    /**
     * @param xml        the xml to parse
     * @param streamer   your fully created streamer of the xml
     * @param <T>        the expected type of your parsed XML
     * @return your parsed XML as an object
     */
    public static <T> T parse(InputStream xml, Streamer<T> streamer) {
        return parse(xml, streamer, getDefaultSEXPXMLReader());
    }

    /**
     * @param xml        the xml to parse
     * @param streamer   your fully created streamer of the xml
     * @param xmlReader  is the interface that an XML parser's SAX2 driver must implement , using this? _bad ass_ alert
     * @param <T>        the expected type of your parsed XML
     * @return your parsed XML as an object
     */
    public static <T> T parse(String xml, Streamer<T> streamer, XMLReader xmlReader) {
        return parse(new ByteArrayInputStream(xml.getBytes()), streamer, xmlReader);
    }

    /**
     * @param xml        the xml to parse
     * @param streamer   your fully created streamer of the xml
     * @param xmlReader  is the interface that an XML parser's SAX2 driver must implement , using this? _bad ass_ alert
     * @param <T>        the expected type of your parsed XML
     * @return your parsed XML as an object
     */
    public static <T> T parse(InputStream xml, final Streamer<T> streamer, XMLReader xmlReader) {
        final CountDownLatch latch = new CountDownLatch(1);
        Instigator latchedInstigator = new Instigator() {
            @Override
            public RootTag getRootTag() {
                return streamer.getRootTag();
            }

            @Override
            public void create(RootElement rootElement) {
                streamer.stream(rootElement);
            }

            @Override
            public void end() {
                latch.countDown();
            }
        };

        RootTag rootTag = streamer.getRootTag();
        RootElement rootElement = new RootElement(rootTag.getNamespace(), rootTag.getTag());
        rootElement.setEndElementListener(latchedInstigator);
        streamer.stream(rootElement);
        xmlReader.setContentHandler(rootElement.getContentHandler());
        XmlParser xmlParser = new XmlParser();
        xmlParser.parse(xml, xmlReader);

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return streamer.getStreamResult();
    }

    private static XMLReader getDefaultSEXPXMLReader() {
        try {
            return new XMLReaderBuilder().allowNamespaceProcessing(true).build();
        } catch (XMLReaderBuilder.XMLReaderCreationException e) {
            throw new RuntimeException(e);
        }
    }

}
