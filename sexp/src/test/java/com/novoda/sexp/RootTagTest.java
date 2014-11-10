package com.novoda.sexp;

import org.junit.Assert;
import org.junit.Test;

public class RootTagTest
{
    @Test
    public void createFactory()
    {
        final String mytag = "MYTAG";
        final RootTag rootTag = RootTag.create(mytag);
        Assert.assertEquals(mytag, rootTag.getTag());
        Assert.assertEquals("", rootTag.getNamespace());
    }

    @Test
    public void createNamespaceFactory()
    {
        final String mytag = "MYTAG";
        final String mynamespace = "MYNAMESPACE";
        final RootTag rootTag = RootTag.create(mytag, mynamespace);
        Assert.assertEquals(mytag, rootTag.getTag());
        Assert.assertEquals(mynamespace, rootTag.getNamespace());
    }
}