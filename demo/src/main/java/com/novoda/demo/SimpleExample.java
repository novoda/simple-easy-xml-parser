package com.novoda.demo;

import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class SimpleExample implements Example {

    //language=XML
    private static final String XML =
            "<novoda>" +
                    "<favouriteColour>Blue</favouriteColour>" +
                    "</novoda>";
    private static ElementFinder<String> elementFinder;

    @Override
    public void execute() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementCreatorFactory();
        elementFinder = factory.getStringFinder();
        Instigator instigator = new SimpleInstigator(elementFinder, finishWatcher);
        SimpleEasyXmlParser.parse(XML, instigator);
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            System.out.println("Found : " + elementFinder.getResult());
        }
    };

    public static class SimpleInstigator extends SimpleTagInstigator {

        public SimpleInstigator(ElementFinder<?> elementFinder, ParseFinishWatcher parseFinishWatcher) {
            super(elementFinder, "favouriteColour", parseFinishWatcher);
        }

        @Override
        public String getRootTag() {
            return "novoda";
        }
    }

}
