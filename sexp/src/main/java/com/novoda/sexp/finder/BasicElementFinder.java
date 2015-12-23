package com.novoda.sexp.finder;

import com.novoda.sax.Element;
import com.novoda.sexp.parser.Parser;

public class BasicElementFinder<T> implements ElementFinder<T> {

    private final Parser<T> parser;
    private T result;

    public BasicElementFinder(Parser<T> parser) {
        this.parser = parser;
        this.result = null;
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
        result = body;
    }

    @Override
    public T getResult() {
        return result;
    }

    @Override
    public T getResultOrThrow() {
        validateResult();
        return result;
    }

    @Override
    public T popResult() {
        T r = result;
        result = null;
        return r;
    }

    @Override
    public T popResultOrThrow() {
        validateResult();
        return popResult();
    }

    private void validateResult() {
        if (result == null) {
            throw new ResultNotFoundException();
        }
    }

    public static class ResultNotFoundException extends RuntimeException {
        public ResultNotFoundException() {
            super("Did you call find() with a valid tag and/or wait for the result to be parsed?");
        }
    }
}
