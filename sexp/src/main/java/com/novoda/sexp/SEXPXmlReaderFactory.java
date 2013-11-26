package com.novoda.sexp;

import org.xml.sax.XMLReader;

class SEXPXmlReaderFactory {

    public static XMLReader getDefault() throws XMLReaderBuilder.XMLReaderCreationException {
        return new SEXPXmlReaderFactory().getDefaultXmlReader();
    }

    private XMLReader getDefaultXmlReader() throws XMLReaderBuilder.XMLReaderCreationException {
        XMLReaderBuilder builder = new XMLReaderBuilder();
        builder.withFeature("http://xml.org/sax/features/namespaces", true);
        return builder.build();
    }

}
