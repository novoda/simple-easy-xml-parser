package com.novoda.sexp.marshaller;

public class StringBodyMarshaller implements BodyMarshaller<String> {

    @Override
    public String marshal(String input) {
        return input;
    }

}
