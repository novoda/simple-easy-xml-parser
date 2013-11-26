package com.novoda.sexp;

import com.novoda.sax.RootElement;

import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.parser.ParseFinishWatcher;

public abstract class SimpleTagInstigator implements Instigator {

    private final ElementFinder<?> elementFinder;
    private final String elementTag;
    private final ParseFinishWatcher parseFinishWatcher;

    public SimpleTagInstigator(ElementFinder<?> elementFinder, String elementTag, ParseFinishWatcher parseFinishWatcher) {
        this.elementFinder = elementFinder;
        this.elementTag = elementTag;
        this.parseFinishWatcher = parseFinishWatcher;
    }

    @Override
    public void create(RootElement element) {
        elementFinder.find(element, elementTag);
    }

    @Override
    public void end() {
        parseFinishWatcher.onFinish();
    }
}
