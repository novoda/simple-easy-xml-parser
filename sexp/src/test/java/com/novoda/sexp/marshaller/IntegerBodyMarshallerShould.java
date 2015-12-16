package com.novoda.sexp.marshaller;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class IntegerBodyMarshallerShould {

    private IntegerBodyMarshaller integerBodyMarshaller;

    @Before
    public void setUp() throws Exception {
        integerBodyMarshaller = new IntegerBodyMarshaller();
    }

    @Test
    public void marshall_strings_to_integers() throws Exception {
        String validInput = "5";
        int expectedOutput = 5;

        assertThat(integerBodyMarshaller.marshall(validInput)).isEqualTo(expectedOutput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_invalid() throws Exception {
        String invalidInput = "invalid";

        integerBodyMarshaller.marshall(invalidInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_float() throws Exception {
        String invalidInput = "0.5f";

        integerBodyMarshaller.marshall(invalidInput);
    }

    @Test(expected = java.lang.NumberFormatException.class)
    public void throw_exception_when_input_is_double() throws Exception {
        String invalidInput = "3.5d";

        integerBodyMarshaller.marshall(invalidInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_null() throws Exception {
        integerBodyMarshaller.marshall(null);
    }
}
