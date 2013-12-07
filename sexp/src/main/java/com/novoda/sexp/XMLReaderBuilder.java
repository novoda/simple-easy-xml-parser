package com.novoda.sexp;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;

public class XMLReaderBuilder {

    private static final String FEATURE_NAMESPACE = "http://xml.org/sax/features/namespaces";
    private static final String FEATURE_NAMESPACE_PREFIX = "http://xml.org/sax/features/namespace-prefixes";

    private final SAXParserFactory saxParserFactory;

    public XMLReaderBuilder() {
        this(SAXParserFactory.newInstance());
    }

    XMLReaderBuilder(SAXParserFactory saxParserFactory) {
        this.saxParserFactory = saxParserFactory;
    }

    /**
     * Sets the particular feature in the underlying implementation of org.xml.sax.XMLReader. http://xerces.apache.org/xerces2-j/features.html
     *
     * @param name The name of the feature to be set.
     * @param value The value of the feature to be set.
     * @return XMLReaderBuilder
     * @throws XMLReaderCreationException
     */

    public XMLReaderBuilder withFeature(String name, boolean value) throws XMLReaderCreationException {
        try {
            saxParserFactory.setFeature(name, value);
        } catch (ParserConfigurationException e) {
            throw new XMLReaderCreationException(e);
        } catch (SAXNotRecognizedException e) {
            throw new XMLReaderCreationException(e);
        } catch (SAXNotSupportedException e) {
            throw new XMLReaderCreationException(e);
        }
        return this;
    }


    /**
     * Perform namespace processing: prefixes will be stripped off element and attribute names and
     * replaced with the corresponding namespace URIs. By default, the two will simply be concatenated,
     * but the namespace-sep core property allows the application to specify a delimiter string for
     * separating the URI part and the local part.
     *
     * @param allowProcessing enables/disables the processing
     * @return XMLReaderBuilder
     * @throws XMLReaderCreationException
     */
    public XMLReaderBuilder allowNamespaceProcessing(boolean allowProcessing) throws XMLReaderCreationException {
        withFeature(FEATURE_NAMESPACE, allowProcessing);
        return this;
    }

    /**
     * Report the original prefixed names and attributes used for namespace declarations.
     *
     * @param allowProcessing enables/disables the processing
     * @return XMLReaderBuilder
     * @throws XMLReaderCreationException
     */
    public XMLReaderBuilder allowAttributeNamespaceProcessing(boolean allowProcessing) throws XMLReaderCreationException {
        withFeature(FEATURE_NAMESPACE_PREFIX, allowProcessing);
        return this;
    }

    /**
     * Specifies that the parser produced by this code will provide support for XML namespaces.
     *
     * @param isAware enables/disables the processing
     * @return XMLReaderBuilder
     */
    public XMLReaderBuilder setNamespaceAware(boolean isAware) {
        saxParserFactory.setNamespaceAware(isAware);
        return this;
    }

    /**
     * Set state of XInclude processing.
     *
     * @param includeAware
     * @return XMLReaderBuilder
     */
    public XMLReaderBuilder setXIncludeAware(boolean includeAware) {
        saxParserFactory.setXIncludeAware(includeAware);
        return this;
    }

    /**
     * Set the {@link Schema} to be used by parsers created
     * from this factory
     *
     * @param schema
     * @return XMLReaderBuilder
     */
    public XMLReaderBuilder setSchema(Schema schema) {
        saxParserFactory.setSchema(schema);
        return this;
    }

    /**
     * Create the XMLReader with the specified options
     *
     * @return XMLReader
     * @throws XMLReaderCreationException
     */
    public XMLReader build() throws XMLReaderCreationException {
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            return saxParser.getXMLReader();
        } catch (ParserConfigurationException e) {
            throw new XMLReaderCreationException(e);
        } catch (SAXException e) {
            throw new XMLReaderCreationException(e);
        }
    }

    public static class XMLReaderCreationException extends Exception {
        public XMLReaderCreationException(Exception e) {
            super(e);
        }
    }

}
