package com.novoda.sexp.parser;

import com.novoda.sax.Element;

import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.marshaller.BodyMarshaller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ListParserShould {

    @Mock
    private ElementFinderFactory mockFactory;
    @Mock
    private BodyMarshaller<Object> mockMarshaller;
    @Mock
    private ParseWatcher<List<Object>> mockListener;
    @Mock
    private ElementFinder<Object> mockListCreator;
    private ListParser<Object> listParser;

    @Before
    public void setUp() {
        initMocks(this);
        stub(mockFactory.getListElementFinder(Mockito.<BodyMarshaller<Object>>any(), Mockito.<ParseWatcher<Object>>any())).toReturn(mockListCreator);

        listParser = new ListParser<Object>("individualItemTag", mockFactory, mockMarshaller);
    }

    @Test
    public void delegateToElementCreatorToParseEachItem_whenParsing() throws Exception {
        Element element = mock(Element.class);
        listParser.parse(element, mockListener);

        verify(mockListCreator).find(element, "individualItemTag");
    }

    @Test
    public void informListener_whenTheWholeListOfElementsHasBeenParsed() {
        Element element = mock(Element.class);
        listParser.parse(element, mockListener);
        listParser.end();

        verify(mockListener).onParsed(Mockito.<List<Object>>any());
    }



}
