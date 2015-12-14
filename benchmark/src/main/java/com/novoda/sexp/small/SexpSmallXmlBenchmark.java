package com.novoda.sexp.small;

import com.novoda.sax.RootElement;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;

public class SexpSmallXmlBenchmark {

    public void parse(String xml) throws Exception {
        ElementFinderFactory finderFactory = SimpleEasyXmlParser.getElementFinderFactory();

        SimpleEasyXmlParser.parse(
                xml,
                new SexpInstigator(
                        finderFactory.getStringFinder(),
                        new SexpInstigator.Callback() {
                            @Override
                            public void onFinish(String result) {
                                System.out.println(SexpSmallXmlBenchmark.this.getClass().getSimpleName() + " " + result);
                            }
                        }
                )
        );
    }

    private static class SexpInstigator implements Instigator {

        private final ElementFinder<String> elementFinder;
        private final Callback callback;

        public SexpInstigator(ElementFinder<String> elementFinder, Callback callback) {
            this.elementFinder = elementFinder;
            this.callback = callback;
        }

        @Override
        public RootTag getRootTag() {
            return RootTag.create("employee");
        }

        @Override
        public void create(RootElement rootElement) {
            elementFinder.find(rootElement, "name");
        }

        @Override
        public void end() {
            String result = elementFinder.getResultOrThrow();
            callback.onFinish(result);
        }

        interface Callback {
            void onFinish(String result);
        }
    }

}
