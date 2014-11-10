package com.novoda.sexp;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class RootTagShould {

    private static final String ROOT = "root";
    private static final String NAMESPACE = "namespace";

    @Test
    public void setRoot_onCreate() throws Exception {
        RootTag rootTag = RootTag.create(ROOT, NAMESPACE);

        assertThat(rootTag.getTag()).isEqualTo(ROOT);
    }

    @Test
    public void setNamespace_onCreate() throws Exception {
        RootTag rootTag = RootTag.create(ROOT, NAMESPACE);

        assertThat(rootTag.getNamespace()).isEqualTo(NAMESPACE);
    }

}
