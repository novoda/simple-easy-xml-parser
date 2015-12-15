package com.novoda.sexp.medium;

import com.novoda.sax.Element;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseWatcher;

import java.util.ArrayList;
import java.util.List;

public class FeedParser {

/*
        <id>1234cat</id>
        <title type="text">
        Programmes Categories
        </title>
        <updated>2015-03-13T10:49:48.142Z</updated>
        <author>
        <name> Bob Smith
        </name>
        </author>
        <logo imageSource="default">http://google.com/xml.gif</logo>
        <link href="http://google.com"rel="self"type="application/atom+xml"/>
        <generator version="2.5">HalBot</generator>
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

    private static final String TAG_ID = "id";
    private static final String TAG_TITLE = "title";
    private static final String TAG_UPDATED = "updated";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_LOGO = "logo";
    private static final String TAG_LINK = "link";
    private static final String TAG_GENERATOR = "generator";
    private static final String TAG_ENTRY = "entry";
    private static final String ATTR_HREF = "href";
    private static final String ATTR_REL = "rel";
    private static final String ATTR_TITLE = "title";
    private static final String ATTR_TYPE = "type";

    private final ElementFinder<String> idFinder;
    private final ElementFinder<String> titleFinder;
    private final ElementFinder<String> updatedFinder;
    private final ElementFinder<SexpMediumXmlBenchmark.Author> authorFinder;
    private final ElementFinder<String> logoFinder;
    private final ElementFinder<String> generatorFinder;
    private final ElementFinder<SexpMediumXmlBenchmark.Link> linkFinder;
    private final ElementFinder<SexpMediumXmlBenchmark.Entry> entryFinder;

    private FeedHolder feedHolder;

    public FeedParser(ElementFinderFactory factory) {
        this.idFinder = factory.getStringFinder();
        this.titleFinder = factory.getStringFinder();
        this.updatedFinder = factory.getStringFinder();
        this.authorFinder = factory.getTypeFinder(new AuthorParser(factory));
        this.logoFinder = factory.getStringFinder();
        this.generatorFinder = factory.getStringFinder();
        this.linkFinder = factory.getAttributeFinder(new LinkAttributeMarshaller(), ATTR_HREF, ATTR_REL, ATTR_TITLE, ATTR_TYPE);
        this.entryFinder = factory.getListElementFinder(new EntryParser(factory), parseWatcher);
    }

    private final ParseWatcher<SexpMediumXmlBenchmark.Entry> parseWatcher = new ParseWatcher<SexpMediumXmlBenchmark.Entry>() {
        @Override
        public void onParsed(SexpMediumXmlBenchmark.Entry entry) {
            feedHolder.entries.add(entry);
        }
    };

    public void parse(Element element) {
        feedHolder = new FeedHolder();
        idFinder.find(element, TAG_ID);
        titleFinder.find(element, TAG_TITLE);
        updatedFinder.find(element, TAG_UPDATED);
        authorFinder.find(element, TAG_AUTHOR);
        logoFinder.find(element, TAG_LOGO);
        generatorFinder.find(element, TAG_GENERATOR);
        linkFinder.find(element, TAG_LINK);
        entryFinder.find(element, TAG_ENTRY);
    }

    public SexpMediumXmlBenchmark.Feed getResult() {
        feedHolder.id = idFinder.getResultOrThrow();
        feedHolder.title = titleFinder.getResultOrThrow();
        feedHolder.updated = updatedFinder.getResultOrThrow();
        feedHolder.author = authorFinder.getResultOrThrow();
        feedHolder.logo = logoFinder.getResultOrThrow();
        feedHolder.generator = generatorFinder.getResultOrThrow();
        feedHolder.link = linkFinder.getResultOrThrow();

        return feedHolder.asFeed();
    }

    private static class FeedHolder {
        private String id;
        private String title;
        private String updated;
        private SexpMediumXmlBenchmark.Author author;
        private String logo;
        private String generator;
        private SexpMediumXmlBenchmark.Link link;
        private List<SexpMediumXmlBenchmark.Entry> entries = new ArrayList<>();

        public SexpMediumXmlBenchmark.Feed asFeed() {
            return new SexpMediumXmlBenchmark.Feed(id, title, updated, author, logo, generator, link, entries);
        }
    }
}
