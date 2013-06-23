package com.novoda.sexp.parser;

import android.sax.Element;

import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.marshaller.BodyMarshaller;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

public class ListParser<T> implements Parser<List<T>>, ListParseWatcher<T> {

    private final String tag;
    private final ElementFinder<T> listCreator;

    private ParseWatcher<List<T>> listener;
    private List<T> list;

    public ListParser(String tag, ElementFinderFactory factory, BodyMarshaller<T> bodyMarshaller) {
        this.tag = tag;
        this.listCreator = factory.getListElementFinder(bodyMarshaller, this);
    }

    @Override
    public void parse(Element element, ParseWatcher<List<T>> listener) {
        this.listener = listener;
        element.setElementListener(this);
        listCreator.find(element, tag);
    }

    @Override
    public void start(Attributes attributes) {
        list = new ArrayList<T>();
    }

    @Override
    public void onParsed(T item) {
        list.add(item);
    }

    @Override
    public void end() {
        listener.onParsed(list);
    }
}
