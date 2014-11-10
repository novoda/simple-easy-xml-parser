package com.novoda.sexp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class XmlParserShould {

    @Mock
    XMLReader xmlReader;

    //language=XML
    private static final String xml =
            "<novoda>" +
                    "<favouriteColour>Blue</favouriteColour>" +
                    "</novoda>";

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void call_reader_parse_when_parse_is_called() throws Exception {
        XmlParser xmlParser = new XmlParser();

        xmlParser.parse(xml, xmlReader);

        verify(xmlReader).parse(Mockito.<InputSource>any());
    }
}
