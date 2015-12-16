package com.novoda.sexp;

import com.novoda.sax.RootElement;
import com.novoda.sexp.finder.ElementFinder;

/**
 * This instigator can be used when you want to return your parsed object synchronously
 * from the element finder. A good example being {@link SimpleEasyXmlParser#parse(String, ElementFinderInstigator)}
 *
 * @param <R> the type of object you expect as a result of xml parsing
 */
public abstract class ElementFinderInstigator<R> implements Instigator {

    private final ElementFinder<R> elementFinder;
    private final String elementTag;

    public ElementFinderInstigator(ElementFinder<R> elementFinder, String elementTag) {
        this.elementFinder = elementFinder;
        this.elementTag = elementTag;
    }

    @Override
    public void create(RootElement element) {
        elementFinder.find(element, elementTag);
    }

    @Override
    public void end() {
        // no callback on completion
    }

    /**
     * @return the object you expected to be parsed from your dependent {@link ElementFinder<R>}
     */
    public R getResultOrThrow() {
        return elementFinder.getResultOrThrow();
    }
}
