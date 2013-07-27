package com.novoda.sexp.finder;

import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class ListElementFinderShould {

    @Mock
    private Parser<Object> mockParser;
    @Mock
    private ParseWatcher<Object> mockWatcher;

    @Mock
    private com.novoda.sax.Element mockElement;
    ListElementFinder<Object> elementCreator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        elementCreator = new ListElementFinder<Object>(mockParser, mockWatcher);
    }

    @Test
    public void callParser_whenCreated() {
        String tag = "tag";
        stub(mockElement.getChild(tag)).toReturn(mockElement);

        elementCreator.find(mockElement, tag);

        verify(mockParser).parse(mockElement, elementCreator);
    }

    @Test
    public void callWatcher_afterEachItemForTheListIsParsed() throws Exception {
        String result = "result";

        elementCreator.onParsed(result);

        verify(mockWatcher).onParsed(result);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void notSupportGetResult_asListCreatorWillCallbackAfterEveryListItemIsParsed() throws Exception {
        elementCreator.requireResult();
    }

}
