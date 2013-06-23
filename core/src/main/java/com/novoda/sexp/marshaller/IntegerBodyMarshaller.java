package com.novoda.sexp.marshaller;

public class IntegerBodyMarshaller implements BodyMarshaller<Integer> {
    @Override
    public Integer marshall(String input) {
        return Integer.valueOf(input);
    }
}
