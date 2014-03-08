package com.novoda.sexp.service;

import com.squareup.otto.Bus;
/**
 * Created by Ianic on 3/8/14.
 */
public class OttoBus {

    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private OttoBus() {
        // No instances.
    }
}
