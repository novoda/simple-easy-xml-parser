package com.novoda.sexp.parser;

import android.sax.Element;

public interface Parser<T> {
    void parse(Element element, ParseWatcher<T> listener);
}

