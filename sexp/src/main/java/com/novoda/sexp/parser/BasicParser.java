package com.novoda.sexp.parser;

import com.novoda.sax.Element;
import com.novoda.sax.EndTextElementListener;
import com.novoda.sexp.marshaller.BodyMarshaller;

public class BasicParser<T> implements Parser<T> {

    private final BodyMarshaller<T> bodyMarshaller;

    public BasicParser(BodyMarshaller<T> bodyMarshaller) {
        this.bodyMarshaller = bodyMarshaller;
    }

    @Override
    public void parse(Element element, final ParseWatcher<T> listener) {
        element.setEndTextElementListener(
                new EndTextElementListener() {
                    @Override
                    public void end(String body) {
                        listener.onParsed(bodyMarshaller.marshall(body));
                    }
                }
        );
    }

}
