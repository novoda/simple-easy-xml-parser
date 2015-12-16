package com.novoda.sexp.parser;

import com.novoda.sax.Element;
import com.novoda.sexp.marshaller.AttributeMarshaller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.xml.sax.Attributes;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class BasicAttributeParserShould {

    private static final String ATTR_TAG = "attrTag";
    private static final String FOUND_ATTRIBUTE = "foundAttribute";

    @Mock
    private AttributeMarshaller<Object> mockMarshaller;
    @Mock
    private ParseWatcher<Object> mockParseWatcher;
    private BasicAttributeParser<Object> cut;

    @Before
    public void setUp() {
        initMocks(this);
        cut = new BasicAttributeParser<Object>(mockMarshaller, ATTR_TAG);
    }

    @Test
    public void setListenerToObtainAttributes() throws Exception {
        Element element = mock(Element.class);
        cut.parse(element, mockParseWatcher);

        verify(element).setStartElementListener(cut);
    }

    @Test
    public void informListener_whenAttributesParsed() throws Exception {
        Element element = mock(Element.class);
        cut.parse(element, mockParseWatcher);
        Attributes attributes = mock(Attributes.class);
        stub(attributes.getValue(ATTR_TAG)).toReturn(FOUND_ATTRIBUTE);
        Object outputObject = new Object();
        stub(mockMarshaller.marshall(FOUND_ATTRIBUTE)).toReturn(outputObject);
        cut.start(attributes);

        verify(mockParseWatcher).onParsed(outputObject);
    }

    @Test
    public void delegateToMarshaller_toConvertToObjectWanted_whenAttributesParsed() throws Exception {
        Element element = mock(Element.class);
        cut.parse(element, mockParseWatcher);
        Attributes attributes = mock(Attributes.class);
        stub(attributes.getValue(ATTR_TAG)).toReturn(FOUND_ATTRIBUTE);
        cut.start(attributes);

        verify(mockMarshaller).marshall(FOUND_ATTRIBUTE);
    }
}

