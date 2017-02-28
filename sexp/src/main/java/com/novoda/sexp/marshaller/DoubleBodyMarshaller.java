package com.novoda.sexp.marshaller;

public class DoubleBodyMarshaller implements BodyMarshaller<Double> {
    @Override
    public Double marshal(String input) {
        return Double.valueOf(input);
    }
}
