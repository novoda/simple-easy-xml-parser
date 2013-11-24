package com.novoda.sexp;

public class RootTag {

    private static final String UNUSED_NAMESPACE = "";

    private final String tag;
    private final String namespace;

    public static RootTag create(String tag) {
        return create(tag, UNUSED_NAMESPACE);
    }

    public static RootTag create(String tag, String namespace) {
        return new RootTag(tag, namespace);
    }

    private RootTag(String tag, String namespace) {
        this.tag = tag;
        this.namespace = namespace;
    }

    public String getTag() {
        return tag;
    }

    public String getNamespace() {
        return namespace;
    }
}
