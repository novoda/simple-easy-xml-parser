package com.novoda.sexp.parser;

import com.novoda.sax.RootElement;

import org.xml.sax.SAXException;

public class ParserHelper {
    public static void mockParse(Parser<Object> parser, ParseWatcher<Object> mockListener) throws SAXException {
        mockParse(parser, mockListener, "");
    }

    public static void mockParse(Parser<Object> parser, ParseWatcher<Object> mockListener, String tagBody) throws SAXException {
        RootElement element = new RootElement("");
        parser.parse(element, mockListener);
        element.getContentHandler().startElement("", "", "", null);
        element.getContentHandler().characters(tagBody.toCharArray(), 0, tagBody.length());
        element.getContentHandler().endElement("", "", "");
    }
}
