package com.novoda.sexp;

import com.novoda.sexp.marshaller.AttributeMarshaller;

public class LinkAttributeMarshaller implements AttributeMarshaller<SexpMediumXmlBenchmark.Link> {
        @Override
        public SexpMediumXmlBenchmark.Link marshall(String... input) {
            SexpMediumXmlBenchmark.Link link = new SexpMediumXmlBenchmark.Link();
            link.href = input[0];
            link.rel = input[1];
            link.title = input[2];
            link.type = input[3];

            return link;
        }
    }
