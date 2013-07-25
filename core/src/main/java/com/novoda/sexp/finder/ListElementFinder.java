package com.novoda.sexp.finder;

import com.novoda.sax.Element;

import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

public class ListElementFinder<T> implements ElementFinder<T> {

    private final Parser<T> parser;
    private final ParseWatcher<T> parseWatcher;

    public ListElementFinder(Parser<T> parser, ParseWatcher<T> parseWatcher) {
        this.parser = parser;
        this.parseWatcher = parseWatcher;
    }

    @Override
    public void find(Element from, String tag) {
        parser.parse(from.getChild(tag), this);
    }

    @Override
    public void find(Element from, String uri, String tag) {
        parser.parse(from.getChild(uri, tag), this);
    }

    @Override
    public void onParsed(T body) {
        parseWatcher.onParsed(body);
    }

    @Override
    public T getResult() {
        throw new UnsupportedOperationException("Has a listener to pass each item as parsed, so there is no result.");
    }

}
