package com.novoda.sexp;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class RootTagShould {

    private String root = "root";
    private String namespace = "namespace";

    @Test
    public void setRoot_onCreate() throws Exception {
        RootTag rootTag = RootTag.create(root, namespace);
        assertThat(rootTag.getTag()).isEqualTo(root);
    }

    @Test
    public void setNamespace_onCreate() throws Exception {
        RootTag rootTag = RootTag.create(root, namespace);
        assertThat(rootTag.getNamespace()).isEqualTo(namespace);
    }

}
