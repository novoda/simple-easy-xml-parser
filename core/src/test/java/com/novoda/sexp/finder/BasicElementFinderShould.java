package com.novoda.sexp.finder;

import com.novoda.sexp.parser.Parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

public class BasicElementFinderShould {

    @Mock
    private Parser<Object> mockParser;
    @Mock
    private android.sax.Element mockElement;

    BasicElementFinder<Object> elementCreator;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        elementCreator = new BasicElementFinder<Object>(mockParser);
    }

    @Test
    public void callParser_whenCreated() {
        String tag = "tag";
        stub(mockElement.getChild(tag)).toReturn(mockElement);

        elementCreator.find(mockElement, tag);

        verify(mockParser).parse(mockElement, elementCreator);
    }

    @Test
    public void create_a_result_when_parsing_finished() throws Exception {
        String result = "result";

        elementCreator.onParsed(result);

        assertThat(elementCreator.getResult()).isEqualTo(result);
    }

    @Test(expected = NullPointerException.class)
    public void throw_exception_when_result_has_not_been_parsed_and_result_is_asked_for() throws Exception {
        elementCreator.getResult();
    }

}
