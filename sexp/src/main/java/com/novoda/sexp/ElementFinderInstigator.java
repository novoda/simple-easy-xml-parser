package com.novoda.sexp;

import com.novoda.sax.RootElement;
import com.novoda.sexp.finder.ElementFinder;

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

    public R getResultOrThrow() {
        return elementFinder.getResultOrThrow();
    }
}
