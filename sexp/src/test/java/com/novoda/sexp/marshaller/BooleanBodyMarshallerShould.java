package com.novoda.sexp.marshaller;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class BooleanBodyMarshallerShould {

    private BooleanBodyMarshaller booleanBodyMarshaller;

    @Before
    public void setUp() throws Exception {
        booleanBodyMarshaller = new BooleanBodyMarshaller();
    }

    @Test
    public void marshal_string_false_to_boolean_false() throws Exception {
        String rawFalse = "false";

        assertThat(booleanBodyMarshaller.marshal(rawFalse)).isFalse();
    }

    @Test
    public void marshal_string_true_to_boolean_true() throws Exception {
        String rawTrue = "true";

        assertThat(booleanBodyMarshaller.marshal(rawTrue)).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_invalid() throws Exception {
        String invalidInput = "invalid";

        booleanBodyMarshaller.marshal(invalidInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_null() throws Exception {
        booleanBodyMarshaller.marshal(null);
    }
}
