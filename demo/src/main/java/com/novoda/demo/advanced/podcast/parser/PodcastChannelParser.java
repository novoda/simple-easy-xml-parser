package com.novoda.demo.advanced.podcast.parser;

import com.novoda.demo.advanced.podcast.pojo.Channel;
import com.novoda.demo.advanced.podcast.pojo.PodcastItem;
import com.novoda.demo.advanced.podcast.pojo.ChannelImage;
import com.novoda.demo.advanced.podcast.pojo.Link;
import com.novoda.demo.advanced.podcast.pojo.Title;
import com.novoda.sax.Element;
import com.novoda.sax.ElementListener;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;

public class PodcastChannelParser implements Parser<Channel> {

    private static final String TAG_ITEM = "item";
    private static final String TAG_TITLE = "title";
    private static final String TAG_LINK = "link";
    private static final String TAG_IMAGE = "image";

    private final ElementFinder<PodcastItem> podcastItemFinder;
    private final ElementFinder<Title> titleFinder;
    private final ElementFinder<Link> linkFinder;
    private final ElementFinder<ChannelImage> imageFinder;

    private ParseWatcher<Channel> listener;
    private ChannelHolder channelHolder;

    public PodcastChannelParser(ElementFinderFactory factory) {
        this.podcastItemFinder = factory.getListElementFinder(new PodcastItemParser(factory), parseWatcher);
        this.titleFinder = factory.getStringWrapperTypeFinder(Title.class);
        this.linkFinder = factory.getStringWrapperTypeFinder(Link.class);
        this.imageFinder = factory.getTypeFinder(new ChannelImageParser(factory));
    }

    private final ParseWatcher<PodcastItem> parseWatcher = new ParseWatcher<PodcastItem>() {
        @Override
        public void onParsed(PodcastItem item) {
            channelHolder.podcastItems.add(item);
        }
    };

    @Override
    public void parse(Element element, final ParseWatcher<Channel> listener) {
        this.listener = listener;
        element.setElementListener(channelParseListener);
        podcastItemFinder.find(element, TAG_ITEM);
        titleFinder.find(element, TAG_TITLE);
        linkFinder.find(element, TAG_LINK);
        imageFinder.find(element, TAG_IMAGE);
    }

    private final ElementListener channelParseListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            channelHolder = new ChannelHolder();
        }

        @Override
        public void end() {
            channelHolder.title = titleFinder.getResult();
            channelHolder.link = linkFinder.getResult();
            channelHolder.image = imageFinder.getResult();

            listener.onParsed(channelHolder.asChannel());
        }
    };

    private static class ChannelHolder {
        private final List<PodcastItem> podcastItems = new ArrayList<PodcastItem>();
        private Title title;
        private Link link;
        private ChannelImage image;

        public Channel asChannel() {
            return new Channel(title, link, image, podcastItems);
        }
    }
}