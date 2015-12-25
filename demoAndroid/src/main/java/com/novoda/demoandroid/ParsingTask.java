package com.novoda.demoandroid;

import android.os.AsyncTask;

import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;

public class ParsingTask extends AsyncTask<Void, Void, Void> {
    private String xmlToParse;
    private Instigator instigator;

    /**
     * ParsingTask constructor
     *
     * @param xml          XML string to parse
     * @param anInstigator Instigator used to parse
     */
    public ParsingTask(String xml, Instigator anInstigator) {
        xmlToParse = xml;
        instigator = anInstigator;
    }

    @Override
    protected Void doInBackground(Void... params) {
        SimpleEasyXmlParser.parse(xmlToParse, instigator);
        return null;
    }
}
