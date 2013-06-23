package com.novoda.sexp.parser;

import com.novoda.sax.Element;

public interface Parser<T> {
    void parse(Element element, ParseWatcher<T> listener);
}

