package com.novoda.sexp.marshaller;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LongBodyMarshallerShould {

    private LongBodyMarshaller longBodyMarshaller;

    @Before
    public void setUp() throws Exception {
        longBodyMarshaller = new LongBodyMarshaller();
    }

    @Test
    public void marshal_integer_strings_to_long() throws Exception {
        String validInput = "5";
        long expectedOutput = 5;

        assertThat(longBodyMarshaller.marshal(validInput)).isEqualTo(expectedOutput);
    }

    @Test
    public void marshal_long_strings_to_long() throws Exception {
        String validInput = String.valueOf(Long.MAX_VALUE);
        long expectedOutput = Long.MAX_VALUE;

        assertThat(longBodyMarshaller.marshal(validInput)).isEqualTo(expectedOutput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_invalid() throws Exception {
        String invalidInput = "invalid";

        longBodyMarshaller.marshal(invalidInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_float() throws Exception {
        String invalidInput = "0.5f";

        longBodyMarshaller.marshal(invalidInput);
    }

    @Test(expected = NumberFormatException.class)
    public void throw_exception_when_input_is_double() throws Exception {
        String invalidInput = "3.5d";

        longBodyMarshaller.marshal(invalidInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_null() throws Exception {
        longBodyMarshaller.marshal(null);
    }
}
