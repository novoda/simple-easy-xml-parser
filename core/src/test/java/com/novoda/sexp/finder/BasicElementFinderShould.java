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
    private com.novoda.sax.Element mockElement;

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
    public void get_child_elements_using_namspace_when_a_namespace_is_provided() throws Exception {
        String tag = "tag";
        String namespace = "namespace";

        elementCreator.find(mockElement, namespace, tag);

        verify(mockElement).getChild(namespace, tag);
    }

    @Test
    public void create_a_result_when_parsing_finished() throws Exception {
        String result = "result";

        elementCreator.onParsed(result);

        assertThat(elementCreator.getResultOrThrow()).isEqualTo(result);
    }

    @Test(expected = NullPointerException.class)
    public void throw_exception_when_result_has_not_been_parsed_and_a_required_result_is_asked_for() throws Exception {
        elementCreator.getResultOrThrow();
    }

    @Test
    public void allow_null_results_when_get_result_is_used() throws Exception {
        Object result = elementCreator.getResult();

        assertThat(result).isNull();
    }

}
