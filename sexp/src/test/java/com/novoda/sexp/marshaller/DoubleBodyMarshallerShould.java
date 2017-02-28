package com.novoda.sexp.marshaller;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class DoubleBodyMarshallerShould {

    private DoubleBodyMarshaller doubleBodyMarshaller;

    @Before
    public void setUp() throws Exception {
        doubleBodyMarshaller = new DoubleBodyMarshaller();
    }

    @Test
    public void marshal_integer_strings_to_long() throws Exception {
        String validInput = "5";
        double expectedOutput = 5;

        assertThat(doubleBodyMarshaller.marshal(validInput)).isEqualTo(expectedOutput);
    }

    @Test
    public void marshal_long_strings_to_long() throws Exception {
        String validInput = String.valueOf(Long.MAX_VALUE);
        double expectedOutput = Long.MAX_VALUE;

        assertThat(doubleBodyMarshaller.marshal(validInput)).isEqualTo(expectedOutput);
    }

    @Test
    public void marshal_float_strings_to_long() throws Exception {
        String invalidInput = "0.5f";
        double expectedOutput = 0.5d;

        assertThat(doubleBodyMarshaller.marshal(invalidInput)).isEqualTo(expectedOutput);
    }

    @Test
    public void marshal_double_strings_to_long() throws Exception {
        String validInput = "120.234d";
        double expectedOutput = 120.234d;

        assertThat(doubleBodyMarshaller.marshal(validInput)).isEqualTo(expectedOutput);
        assertThat(doubleBodyMarshaller.marshal(validInput)).isEqualTo(expectedOutput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_invalid() throws Exception {
        String invalidInput = "invalid";

        doubleBodyMarshaller.marshal(invalidInput);
    }

    @Test(expected = NullPointerException.class)
    public void throw_exception_when_input_is_null() throws Exception {
        doubleBodyMarshaller.marshal(null);
    }
}
