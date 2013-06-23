package com.novoda.sexp;

import android.sax.EndElementListener;
import android.sax.RootElement;

/**
 * {@link SimpleTagInstigator } is an example implementation
 */
public interface Instigator extends EndElementListener {
    /**
     * @return The root tag of your XML file
     */
    String getRootTag();

    /**
     * This is where you create the 'tree' of
     * {@link com.novoda.sexp.finder.ElementFinder ElementFinder}
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
    void create(RootElement rootElement);

    /**
     * Called when the corresponding closing root tag of your XML file is found
     */
    @Override
    void end();
}
