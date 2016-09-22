package com.novoda.sexp.parser;

import com.novoda.sax.EndTextElementListener;
import com.novoda.sexp.marshaller.BodyMarshaller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static com.novoda.sexp.parser.ParserHelper.mockParse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class BasicParserShould {

    @Mock
    private BodyMarshaller<Object> mockMarshaller;
    @Mock
    private com.novoda.sax.Element mockElement;
    @Mock
    private ParseWatcher<Object> mockListener;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void listenForBodyText_whenParsing() throws Exception {
        BasicParser<Object> basicParser = new BasicParser<Object>(mockMarshaller);

        basicParser.parse(mockElement, mockListener);

        verify(mockElement).setEndTextElementListener(any(EndTextElementListener.class));
    }

    @Test
    public void informListener_whenElementIsParsed() throws Exception {
        BasicParser<Object> basicParser = new BasicParser<Object>(mockMarshaller);

        mockParse(basicParser, mockListener);

        verify(mockListener).onParsed(any());
    }

    @Test
    public void delegateToMarshaller_whenElementIsParsed() throws Exception {
        BasicParser<Object> basicParser = new BasicParser<Object>(mockMarshaller);

        mockParse(basicParser, mockListener);

        verify(mockMarshaller).marshal(any(String.class));
    }
}
