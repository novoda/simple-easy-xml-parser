package com.novoda.sexp;

import com.novoda.sax.RootElement;

/**
 * This instigator can be used when you want to return your parsed object synchronously.
 * A good example being {@link SimpleEasyXmlParser#parse(String, Traverser)}
 *
 * @param <R> the type of object you expect as a result of xml parsing
 */
public interface Traverser<R> {
    /**
     * @return The root tag of your XML file
     */
    RootTag getRootTag();

    /**
     * This is where you traverse the xml 'tree' by using
     * {@link com.novoda.sexp.finder.ElementFinder ElementFinder}
     * or {@link com.novoda.sexp.parser.Parser Parser}
     * objects to parse the XML.<br/><br/>
     * You can use the {@link com.novoda.sexp.finder.ElementFinderFactory ElementFinderFactory}
     * to create tree crawlers, or you can create your own if you implement one of:
     * <ul>
     * <li>{@link com.novoda.sexp.finder.ElementFinder ElementFinder}</li>
     * <li>{@link com.novoda.sexp.marshaller.AttributeMarshaller AttributeMarshaller}</li>
     * <li>{@link com.novoda.sexp.marshaller.BodyMarshaller BodyMarshaller}</li>
     * <li>{@link com.novoda.sexp.parser.Parser Parser}</li>
     * <li>{@link com.novoda.sexp.parser.ParseWatcher ParseWatcher}</li>
     * <li>{@link com.novoda.sexp.parser.ListParseWatcher ListParseWatcher}</li>
     * </ul>
     *
     * @param rootElement the root element of your XML file
     */
    void traverse(RootElement rootElement);

    /**
     * @return the object you expected to be parsed
     */
    R getResultOrThrow();
}
