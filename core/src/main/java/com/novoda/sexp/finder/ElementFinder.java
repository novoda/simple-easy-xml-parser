package com.novoda.sexp.finder;

import com.novoda.sax.Element;

import com.novoda.sexp.parser.ParseWatcher;

public interface ElementFinder<R> extends ParseWatcher<R> {
    void find(Element from, String tag);
    void find(Element from, String uri, String tag);

    @Override
    void onParsed(R item);

    R getResult();
}