package com.novoda.sexp.marshaller;

public class StringBodyMarshaller implements BodyMarshaller<String> {

    @Override
    public String marshall(String input) {
        return input;
    }

}
