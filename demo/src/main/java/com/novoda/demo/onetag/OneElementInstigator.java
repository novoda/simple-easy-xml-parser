package com.novoda.demo.onetag;

import com.novoda.sax.EndTextElementListener;
import com.novoda.sax.RootElement;

import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.parser.ParseFinishWatcher;

/**
 * Because this is a rare case, it is actually harder to setup the parsing of
 * one single XML element, but it is possible with the below Instigator:
 */
public class OneElementInstigator implements Instigator {

    private final ElementFinder<String> elementFinder;
    private final String elementTag;
    private final ParseFinishWatcher parseFinishWatcher;

    public OneElementInstigator(ElementFinder<String> elementFinder, String elementTag, ParseFinishWatcher parseFinishWatcher) {
        this.elementFinder = elementFinder;
        this.elementTag = elementTag;
        this.parseFinishWatcher = parseFinishWatcher;
    }

    @Override
    public RootTag getRootTag() {
        return RootTag.create(elementTag);
    }

    @Override
    public void create(RootElement element) {
        element.setEndTextElementListener(new EndTextElementListener() {
            @Override
            public void end(String body) {
                elementFinder.onParsed(body);
                parseFinishWatcher.onFinish();
            }
        });
    }

    @Override
    public void end() {
        // unused in an XML file with only one tag as we need the body of the element
    }
}
