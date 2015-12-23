package com.novoda.sexp.finder;

import com.novoda.sax.Element;
import com.novoda.sexp.parser.ParseWatcher;

public interface ElementFinder<R> extends ParseWatcher<R> {

    /**
     * Find the given tag inside the given element
     *
     * @param from the XML element to search within
     * @param tag  the XML tag to be searched for
     */
    void find(Element from, String tag);

    /**
     * Find the given tag inside the given element
     *
     * @param from the XML element to search within
     * @param uri  the XML namespace of the searched for tag
     * @param tag  the XML tag to be searched for
     */
    void find(Element from, String uri, String tag);

    @Override
    void onParsed(R item);

    /**
     * @return the result of the parsing if the parsing was completed and tag was found
     * or null if the tag was not found or parsing didn't complete.
     */
    R getResult();

    /**
     * @return the result of the parsing if the parsing was completed and tag was found
     * or throws an exception if the tag was not found or parsing didn't complete.
     */
    R getResultOrThrow();

    /**
     * Same as {@link #getResult} but clears the result instead of keeping it.
     * @return the result of the parsing if the parsing was completed and tag was found
     * or null if the tag was not found or parsing didn't complete.
     */
    R popResult();

    /**
     * Same as {@link #getResultOrThrow} but clears the result instead of keeping it.
     * @return the result of the parsing if the parsing was completed and tag was found
     * or throws an exception if the tag was not found or parsing didn't complete.
     */
    R popResultOrThrow();
}
