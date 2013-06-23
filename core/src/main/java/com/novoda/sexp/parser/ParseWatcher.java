package com.novoda.sexp.parser;

public interface ParseWatcher<T> {
    void onParsed(T item);
}
