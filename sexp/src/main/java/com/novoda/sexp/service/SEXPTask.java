package com.novoda.sexp.service;

import com.novoda.sax.RootElement;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.XMLReaderBuilder;
import com.novoda.sexp.XmlParser;

import org.xml.sax.XMLReader;

import java.io.ByteArrayInputStream;

public abstract class SEXPTask implements Runnable {

    public static final int MASK_API_ID = 0x7FF00000;
    Thread mCurrentThread;
    private static SEXPQueue sQueue;

    public SEXPTask(){
        sQueue = SEXPQueue.getInstance();
    }

    @Override
    public void run() {
        setCurrentThread(Thread.currentThread());
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        if (Thread.interrupted()) {
            return;
        }

        //Parsing
        Instigator instigator = getInstigator();
        RootTag rootTag = instigator.getRootTag();
        RootElement rootElement = new RootElement(rootTag.getNamespace(), rootTag.getTag());
        rootElement.setEndElementListener(instigator);
        instigator.create(rootElement);
        getXmlReader().setContentHandler(rootElement.getContentHandler());
        XmlParser xmlParser = new XmlParser();

        if (Thread.interrupted()) {
            return;
        }

        xmlParser.parse(getInputStream(), getXmlReader());

        onPostExecute();
    }

    private static XMLReader getDefaultSEXPXMLReader() {
        try {
            return new XMLReaderBuilder().allowNamespaceProcessing(true).build();
        } catch (XMLReaderBuilder.XMLReaderCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCurrentThread(Thread thread) {
        synchronized(this) {
            mCurrentThread = thread;
        }
    }

    public Thread getCurrentThread() {
        synchronized(this) {
            return mCurrentThread;
        }
    }

    public int getType(){
        return MASK_API_ID & getId();
    }

    protected abstract void onPostExecute();
    protected abstract Instigator getInstigator();
    protected abstract ByteArrayInputStream getInputStream();
    protected abstract XMLReader getXmlReader();
    public abstract int getId();

}
