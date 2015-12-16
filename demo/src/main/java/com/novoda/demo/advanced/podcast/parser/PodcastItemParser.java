package com.novoda.demo.advanced.podcast.parser;

import com.novoda.demo.advanced.podcast.pojo.Author;
import com.novoda.demo.advanced.podcast.pojo.Link;
import com.novoda.demo.advanced.podcast.pojo.PodcastItem;
import com.novoda.demo.advanced.podcast.pojo.Title;
import com.novoda.demo.advanced.podcast.pojo.itunes.ItunesDuration;
import com.novoda.sax.Element;
import com.novoda.sax.ElementListener;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.marshaller.AttributeMarshaller;
import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import org.xml.sax.Attributes;

public class PodcastItemParser implements Parser<PodcastItem> {

    private static final String TAG_TITLE = "title";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_LINK = "link";
    private static final String TAG_ITUNES_DURATION = "duration";
    private static final String TAG_ITUNES_IMAGE = "image";
    private static final String TAG_ITUNES_IMAGE_ATTR = "href";
    private static final String TAG_ITUNES_NAMESPACE = "http://www.itunes.com/dtds/podcast-1.0.dtd";

    private final ElementFinder<Author> authorFinder;
    private final ElementFinder<Link> linkFinder;
    private final ElementFinder<Title> titleFinder;
    private final ElementFinder<ItunesDuration> itunesDurationFinder;
    private final ElementFinder<Link> itunesImageFinder;

    private ItemHolder itemHolder;
    private ParseWatcher<PodcastItem> listener;

    public PodcastItemParser(ElementFinderFactory factory) {
        this.titleFinder = factory.getStringWrapperTypeFinder(Title.class);
        this.authorFinder = factory.getStringWrapperTypeFinder(Author.class);
        this.linkFinder = factory.getStringWrapperTypeFinder(Link.class);
        itunesDurationFinder = factory.getStringWrapperTypeFinder(ItunesDuration.class);
        itunesImageFinder = factory.getAttributeFinder(new ImageAttributeMarshaller(), TAG_ITUNES_IMAGE_ATTR);
    }

    @Override
    public void parse(Element element, final ParseWatcher<PodcastItem> listener) {
        this.listener = listener;
        element.setElementListener(itemParseListener);
        titleFinder.find(element, TAG_TITLE);
        authorFinder.find(element, TAG_AUTHOR);
        linkFinder.find(element, TAG_LINK);
        itunesDurationFinder.find(element, TAG_ITUNES_NAMESPACE, TAG_ITUNES_DURATION);
        itunesImageFinder.find(element, TAG_ITUNES_NAMESPACE, TAG_ITUNES_IMAGE);
    }

    private final ElementListener itemParseListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            itemHolder = new ItemHolder();
        }

        @Override
        public void end() {
            itemHolder.title = titleFinder.getResultOrThrow();
            itemHolder.author = authorFinder.getResultOrThrow();
            itemHolder.link = linkFinder.getResultOrThrow();
            itemHolder.image = itunesImageFinder.getResultOrThrow();
            itemHolder.itunesDuration = itunesDurationFinder.getResultOrThrow();

            listener.onParsed(itemHolder.asItem());
        }
    };

    private static class ItemHolder {
        Title title;
        Author author;
        Link link;
        Link image;
        ItunesDuration itunesDuration;

        public PodcastItem asItem() {
            return new PodcastItem(title, author, image, link, itunesDuration);
        }
    }

    private static class ImageAttributeMarshaller implements AttributeMarshaller<Link> {
        @Override
        public Link marshall(String... input) {
            return new Link(input[0]);
        }
    }
}
