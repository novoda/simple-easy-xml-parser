package com.novoda.demo;

import android.sax.EndTextElementListener;
import android.sax.RootElement;

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
            System.out.println("Found : " + elementFinder.getResult());
        }
    };

    public static class OneElementInstigator implements Instigator {

        private final ElementFinder<String> elementFinder;
        private final String elementTag;
        private final ParseFinishWatcher parseFinishWatcher;

        public OneElementInstigator(ElementFinder<String> elementFinder, String elementTag, ParseFinishWatcher parseFinishWatcher) {
            this.elementFinder = elementFinder;
            this.elementTag = elementTag;
            this.parseFinishWatcher = parseFinishWatcher;
        }

        @Override
        public String getRootTag() {
            return elementTag;
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

}
