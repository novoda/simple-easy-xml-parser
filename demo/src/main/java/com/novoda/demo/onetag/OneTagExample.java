package com.novoda.demo.onetag;

import com.novoda.demo.Example;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class OneTagExample implements Example {

    //language=XML
    private static final String XML = "<novoda>Hello XML</novoda>";
    private static ElementFinder<String> elementFinder;

    @Override
    public void execute() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();
        elementFinder = factory.getStringFinder();
        Instigator instigator = new OneElementInstigator(elementFinder, "novoda", finishWatcher);
        SimpleEasyXmlParser.parse(XML, instigator);
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            System.out.println("Found : " + elementFinder.getResultOrThrow());
        }
    };

}
