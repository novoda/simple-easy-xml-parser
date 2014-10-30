package com.novoda.sexp.marshaller;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class StringBodyMarshallerShould {

    StringBodyMarshaller stringBodyMarshaller;

    @Before
    public void setUp() {
        stringBodyMarshaller = new StringBodyMarshaller();
    }

    @Test
    public void marshallTheString() throws Exception {
        String theInput = "theInput";

        assertThat(stringBodyMarshaller.marshall(theInput)).isEqualTo(theInput);
    }

    @Test
    public void marshallNull() throws Exception {
        assertThat(stringBodyMarshaller.marshall(null)).isEqualTo(null);
    }
}
