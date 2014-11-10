package com.novoda.sexp;

import com.novoda.sax.EndTextElementListener;
import com.novoda.sexp.parser.BasicParser;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by joaobiriba on 30/10/14.
 */
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
