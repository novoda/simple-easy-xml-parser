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
import com.novoda.sexp.ElementFinderInstigator;
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
        SimpleInstigator instigator = new SimpleInstigator(elementFinder, "favouriteColour");

        new ParsingTask(XML, instigator).execute();
    }

    public static class SimpleInstigator extends ElementFinderInstigator<String> {

        public SimpleInstigator(ElementFinder<String> elementFinder, String elementTag) {
            super(elementFinder, elementTag);
        }

        @Override
        public RootTag getRootTag() {
            return RootTag.create("novoda");
        }
    }

    public class ParsingTask extends AsyncTask<Void, Void, String> {
        private String xmlToParse;
        private SimpleInstigator instigator;

        public ParsingTask(String xml, SimpleInstigator anInstigator) {
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
