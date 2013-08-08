package com.novoda.sexp.marshaller;

public interface BodyMarshaller<T> {
    T marshall(String input);
}
