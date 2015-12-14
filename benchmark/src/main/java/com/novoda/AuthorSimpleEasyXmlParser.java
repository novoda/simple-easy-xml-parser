package com.novoda;

import com.novoda.SexpMediumXmlBenchmark.Author;
import com.novoda.sax.Element;
import com.novoda.sax.ElementListener;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import org.xml.sax.Attributes;

public class AuthorSimpleEasyXmlParser implements Parser<Author> {

    private static final String TAG_NAME = "name";

    private final ElementFinder<String> nameFinder;

    private ParseWatcher<Author> listener;

    public AuthorSimpleEasyXmlParser(ElementFinderFactory factory) {
        nameFinder = factory.getStringFinder();
    }

    @Override
    public void parse(Element element, ParseWatcher<Author> listener) {
        this.listener = listener;

        element.setElementListener(authorListener);

        nameFinder.find(element, TAG_NAME);
    }

    private final ElementListener authorListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            // no op
        }

        @Override
        public void end() {
            Author author = new Author(nameFinder.getResultOrThrow());
            listener.onParsed(author);
        }

    };
}
