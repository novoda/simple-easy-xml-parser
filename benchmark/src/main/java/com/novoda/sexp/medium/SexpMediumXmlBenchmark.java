package com.novoda.sexp.medium;

import com.novoda.sax.RootElement;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.finder.ElementFinderFactory;

import java.util.List;

public class SexpMediumXmlBenchmark {

    private final FeedSimpleEasyXmlParser parser;

    public SexpMediumXmlBenchmark() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();
        parser = new FeedSimpleEasyXmlParser(factory);
    }

    public void parse(String xml) throws Exception {
        Instigator instigator = new FeedInstigator(
                parser,
                new FeedInstigator.Callback() {
                    @Override
                    public void onFinish(Feed feed) {
                        System.out.println(SexpMediumXmlBenchmark.this.getClass().getSimpleName() + " " + feed);
                    }
                }
        );

        SimpleEasyXmlParser.parse(xml, instigator);

    }

    public static class FeedInstigator implements Instigator {

        private final FeedSimpleEasyXmlParser parser;
        private final Callback callback;

        public FeedInstigator(FeedSimpleEasyXmlParser parser, Callback callback) {
            this.parser = parser;
            this.callback = callback;
        }

        @Override
        public RootTag getRootTag() {
            return RootTag.create("feed");
        }

        @Override
        public void create(RootElement rootElement) {
            parser.parse(rootElement);
        }

        @Override
        public void end() {
            Feed end = parser.getResult();
            callback.onFinish(end);
        }

        interface Callback {
            void onFinish(Feed feed);
        }
    }

    public static class Feed {
        public String id;
        public String title;
        public String updated;
        public Author author;
        public String logo;
        public Link link;
        public String generator;
        public List<Entry> entries;

        public Feed(String id, String title, String updated, Author author, String logo, String generator, Link link, List<Entry> entries) {
            this.id = id;
            this.title = title;
            this.updated = updated;
            this.author = author;
            this.logo = logo;
            this.generator = generator;
            this.link = link;
            this.entries = entries;
        }

        public String toString() {
            return "Feed{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", updated='" + updated + '\'' +
                    ", author=" + author +
                    ", logo='" + logo + '\'' +
                    ", link='" + link + '\'' +
                    ", generator='" + generator + '\'' +
                    ", entries=" + entries +
                    '}';
        }
    }

    public static class Entry {
        public String id;
        public String title;
        public String summary;
        public String updated;
        public List<Link> links;

        @Override
        public String toString() {
            return "Entry{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", summary='" + summary + '\'' +
                    ", updated='" + updated + '\'' +
                    ", links=" + links +
                    '}';
        }
    }

    public static class Link {
        public String href;
        public String title;
        public String rel;
        public String type;

        @Override
        public String toString() {
            return "Link{" +
                    "url='" + href + '\'' +
                    ", title='" + title + '\'' +
                    ", rel='" + rel + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static class Author {
        private final String name;

        public Author(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
