package com.novoda;

import com.novoda.SexpMediumXmlBenchmark.Link;
import com.novoda.sexp.marshaller.AttributeMarshaller;

public class LinkAttributeMarshaller implements AttributeMarshaller<Link> {
        @Override
        public Link marshall(String... input) {
            Link link = new Link();
            link.href = input[0];
            link.rel = input[1];
            link.title = input[2];
            link.type = input[3];

            return link;
        }
    }
