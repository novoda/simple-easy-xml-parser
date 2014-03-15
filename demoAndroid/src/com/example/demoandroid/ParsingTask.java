package com.example.demoandroid;

import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;

public class ParsingTask implements Runnable {
	private String xmlToParse;
	private Instigator instigator;

	/**
	 * ParsingTask constructor
	 * 
	 * @param xml
	 *            XML string to parse
	 * @param anInstigator
	 *            Instigator used to parse
	 */
	public ParsingTask(String xml, Instigator anInstigator) {
		xmlToParse = xml;
		instigator = anInstigator;
	}

	@Override
	public void run() {
		SimpleEasyXmlParser.parse(xmlToParse, instigator);
	}
}
