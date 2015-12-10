package com.novoda;

import com.fasterxml.jackson.xml.XmlMapper;

import java.util.List;

public class JacksonMediumXmlBenchmark {

    public void parse(String xml) throws Exception {
        XmlMapper mapper = new XmlMapper();
        Feed feed = mapper.readValue(xml, Feed.class);
        System.out.println(getClass().getSimpleName() + " " + feed);
    }

    public static class Feed {
        public String id;

        public String title;
        public String updated;
        public Author author;
        public String logo;
        public String link;
        public String generator;
        public List<Entry> entries;

        @Override
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

    public static class Author {
        public String name;

        @Override
        public String toString() {
            return "Author{" +
                    "name='" + name + '\'' +
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
        public String url;
        public String title;

        @Override
        public String toString() {
            return "Link{" +
                    "url='" + url + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
