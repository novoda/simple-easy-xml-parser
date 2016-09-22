package com.novoda.sexp.marshaller;

public class BooleanBodyMarshaller implements BodyMarshaller<Boolean> {
    @Override
    public Boolean marshal(String input) {
        if ("true".equalsIgnoreCase(input)) {
            return true;
        } else if ("false".equalsIgnoreCase(input)) {
            return false;
        }
        throw new IllegalArgumentException("The input '" + input + "' is not a valid boolean (possible values are 'true' and 'false').");
    }
}
