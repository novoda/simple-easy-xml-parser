package com.novoda.sexp.service;

import java.io.ByteArrayInputStream;

import org.xml.sax.XMLReader;

import android.util.Log;

import com.novoda.sax.RootElement;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.XMLReaderBuilder;
import com.novoda.sexp.XmlParser;

public abstract class SEXPTask implements Runnable {

	public static boolean DEBUG = SEXPBaseService.DEBUG;
    private static final String TAG = SEXPTask.class.getSimpleName();
	
    public static final int MASK_API_ID = 0x7FF00000;
    public static final String XML_STRING = "com.novoda.sexp.service.XML_STRING";
    public static final String ENCODING_STRING = "com.novoda.sexp.service.XML_STRING";
    
    private Thread mCurrentThread = null;
    private static SEXPQueue sQueue;

    public SEXPTask(){
        sQueue = SEXPQueue.getInstance();
    }

    @Override
    public void run() {
        setCurrentThread(Thread.currentThread());
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);

        Log.d(TAG, "TASK RUNNING. ID: " + this.getId());
        if (Thread.interrupted()) {
        	Log.d(TAG,"Thread interrupted. Id: " + this.getId());
            return;
        }

        //Parsing
        Instigator instigator = getInstigator();
        RootTag rootTag = instigator.getRootTag();
        XMLReader xmlReader = getXmlReader();
        RootElement rootElement = new RootElement(rootTag.getNamespace(), rootTag.getTag());
        rootElement.setEndElementListener(instigator);
        instigator.create(rootElement);
        xmlReader.setContentHandler(rootElement.getContentHandler());
        XmlParser xmlParser = new XmlParser();

        if (Thread.interrupted()) {
        	Log.d(TAG,"Thread interrupted. Id: " + this.getId());
            return;
        }

        xmlParser.parse(getInputStream(), xmlReader);
    }

    protected static XMLReader getDefaultSEXPXMLReader() {
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

    public static int getType(int id){
        return MASK_API_ID & id;
    }

    protected abstract Instigator getInstigator();
    protected abstract ByteArrayInputStream getInputStream();
    protected abstract XMLReader getXmlReader();
    public abstract int getId();

}
