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
    public void marshalTheString() throws Exception {
        String theInput = "theInput";

        assertThat(stringBodyMarshaller.marshal(theInput)).isEqualTo(theInput);
    }

    @Test
    public void marshalNull() throws Exception {
        assertThat(stringBodyMarshaller.marshal(null)).isEqualTo(null);
    }

}
