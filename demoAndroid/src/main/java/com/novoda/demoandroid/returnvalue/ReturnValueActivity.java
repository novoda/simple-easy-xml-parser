package com.novoda.demoandroid.returnvalue;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novoda.demoandroid.R;
import com.novoda.demoandroid.SecondLevelBaseActivity;
import com.novoda.sax.RootElement;
import com.novoda.sexp.Streamer;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;

public class ReturnValueActivity extends SecondLevelBaseActivity {
    // language=XML
    private static final String XML = "<novoda>"
            + "<favouriteColour>Blue</favouriteColour>" + "</novoda>";

    private TextView parsingResult;
    private ProgressBar progressBar;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_parsing);

        parsingResult = (TextView) findViewById(R.id.tv_result);
        progressBar = (ProgressBar) findViewById(R.id.pb_oneTag);
        container = (LinearLayout) findViewById(R.id.ll_oneTag);

        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();
        ElementFinder<String> elementFinder = factory.getStringFinder();
        SimpleStreamer instigator = new SimpleStreamer(elementFinder, "favouriteColour");

        new ParsingTask(XML, instigator).execute();
    }

    private static class SimpleStreamer implements Streamer<String> {

        private final ElementFinder<String> elementFinder;
        private final String elementTag;

        public SimpleStreamer(ElementFinder<String> elementFinder, String elementTag) {
            this.elementFinder = elementFinder;
            this.elementTag = elementTag;
        }

        @Override
        public RootTag getRootTag() {
            return RootTag.create("novoda");
        }

        @Override
        public void stream(RootElement rootElement) {
            elementFinder.find(rootElement, elementTag);
        }

        @Override
        public String getStreamResult() {
            return elementFinder.getResultOrThrow();
        }
    }

    private class ParsingTask extends AsyncTask<Void, Void, String> {
        private String xmlToParse;
        private SimpleStreamer instigator;

        public ParsingTask(String xml, SimpleStreamer anInstigator) {
            xmlToParse = xml;
            instigator = anInstigator;
        }

        @Override
        protected String doInBackground(Void... params) {
            return SimpleEasyXmlParser.parse(xmlToParse, instigator);
        }

        @SuppressLint("SetTextI18n") // Not in demo scope
        @Override
        protected void onPostExecute(String result) {
            parsingResult.setText("Got " + result + " as a return value (not using a callback).");
            container.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

}
