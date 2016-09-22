package com.novoda.sexp.marshaller;

public interface BodyMarshaller<T> {
    T marshal(String input);
}
