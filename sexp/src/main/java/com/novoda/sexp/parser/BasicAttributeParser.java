package com.novoda.sexp.parser;

import com.novoda.sax.Element;
import com.novoda.sax.StartElementListener;
import com.novoda.sexp.marshaller.AttributeMarshaller;

import org.xml.sax.Attributes;

public class BasicAttributeParser<T> implements Parser<T>, StartElementListener {

    private final AttributeMarshaller<T> attributeMarshaller;
    private final String[] attrs;
    private ParseWatcher<T> listener;

    public BasicAttributeParser(AttributeMarshaller<T> attributeMarshaller, String... attrs) {
        this.attributeMarshaller = attributeMarshaller;
        this.attrs = attrs;
    }

    @Override
    public void parse(Element element, final ParseWatcher<T> listener) {
        this.listener = listener;
        element.setStartElementListener(this);
    }

    @Override
    public void start(Attributes attributes) {
        String[] values = getAttributeValues(attributes);
        listener.onParsed(attributeMarshaller.marshal(values));
    }

    private String[] getAttributeValues(Attributes attributes) {
        String[] values = new String[attrs.length];
        for (int i = 0; i < attrs.length; i++) {
            values[i] = attributes.getValue(attrs[i]);
        }
        return values;
    }
}
