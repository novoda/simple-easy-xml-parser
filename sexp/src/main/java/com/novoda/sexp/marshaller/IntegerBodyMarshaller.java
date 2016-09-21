package com.novoda.sexp.marshaller;

public class IntegerBodyMarshaller implements BodyMarshaller<Integer> {
    @Override
    public Integer marshal(String input) {
        return Integer.valueOf(input);
    }
}
