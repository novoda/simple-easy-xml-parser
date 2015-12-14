package com.novoda;

import com.novoda.SexpMediumXmlBenchmark.Link;
import com.novoda.sax.Element;
import com.novoda.sax.ElementListener;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

import static com.novoda.SexpMediumXmlBenchmark.Entry;

public class EntrySimpleEasyXmlParser implements Parser<SexpMediumXmlBenchmark.Entry> {

    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_SUMMARY = "summary";
    private static final String TAG_UPDATE = "updated";
    private static final String TAG_LINK = "link";
    private static final String ATTR_HREF = "href";
    private static final String ATTR_REL = "rel";
    private static final String ATTR_TITLE = "title";
    private static final String ATTR_TYPE = "type";

    private final ElementFinder<String> idFinder;
    private final ElementFinder<String> titleFinder;
    private final ElementFinder<String> summaryFinder;
    private final ElementFinder<String> updatedFinder;
    private final ElementFinder<Link> linkFinder;

    private ParseWatcher<Entry> listener;
    private final List<Link> linkList = new ArrayList<>();

        /*
    <entry>
        <id>1234:categories/comedy</id>
        <title type="text">Comedy</title>
        <summary type="html">
        Comedy Programmes
        </summary>
        <updated>2015-03-13T10:49:48.142Z</updated>
        <link href="http://google.com"rel="self"title="A"type="application/atom+xml"/>
        <link href="http://google.com"rel="related"title="B"type="application/atom+xml"/>
        <link href="http://google.com"rel="related"title="C"type="application/atom+xml"/>
        <link href="http://google.com"rel="related"title="D"type="application/atom+xml"/>
        <link href="http://google.com"rel="related"title="E"type="application/atom+xml"/>
        <link href="http://google.com"rel="related"title="F"type="application/atom+xml"/>
        </entry>
     */

    public EntrySimpleEasyXmlParser(ElementFinderFactory factory) {
        idFinder = factory.getStringFinder();
        titleFinder = factory.getStringFinder();
        summaryFinder = factory.getStringFinder();
        updatedFinder = factory.getStringFinder();
        linkFinder = factory.getListAttributeFinder(new LinkAttributeMarshaller(), linkParseWatcher, ATTR_HREF, ATTR_REL, ATTR_TITLE, ATTR_TYPE);
    }

    private final ParseWatcher<Link> linkParseWatcher = new ParseWatcher<Link>() {
        @Override
        public void onParsed(Link link) {
            linkList.add(link);
        }
    };


    @Override
    public void parse(Element element, ParseWatcher<Entry> listener) {
        this.listener = listener;

        element.setElementListener(entryListener);

        idFinder.find(element, TAG_ID);
        titleFinder.find(element, TAG_TITLE);
        summaryFinder.find(element, TAG_SUMMARY);
        updatedFinder.find(element, TAG_UPDATE);
        linkFinder.find(element, TAG_LINK);

    }

    private final ElementListener entryListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            linkList.clear();
        }

        @Override
        public void end() {
            Entry entry = new Entry();
            entry.id = idFinder.getResultOrThrow();
            entry.summary = summaryFinder.getResultOrThrow();
            entry.title = titleFinder.getResultOrThrow();
            entry.updated = updatedFinder.getResultOrThrow();
            entry.links = new ArrayList<>(linkList);

            listener.onParsed(entry);
        }
    };
}
