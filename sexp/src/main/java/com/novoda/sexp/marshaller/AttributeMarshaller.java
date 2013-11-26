package com.novoda.sexp.marshaller;

public interface AttributeMarshaller<T> {
    T marshall(String... input);
}
