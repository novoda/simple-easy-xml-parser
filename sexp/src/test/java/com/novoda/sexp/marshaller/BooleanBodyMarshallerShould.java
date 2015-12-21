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
    public void marshall_strings_to_booleans() throws Exception {
        String validInput = "true";

        assertThat(booleanBodyMarshaller.marshall(validInput)).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_invalid() throws Exception {
        String invalidInput = "invalid";

        booleanBodyMarshaller.marshall(invalidInput);
    }

    @Test(expected = IllegalArgumentException.class)
    public void throw_exception_when_input_is_null() throws Exception {
        booleanBodyMarshaller.marshall(null);
    }
}
