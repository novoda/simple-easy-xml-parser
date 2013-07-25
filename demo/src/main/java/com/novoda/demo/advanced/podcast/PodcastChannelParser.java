package com.novoda.demo.advanced.podcast;

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

    private final ElementFinder<PodcastItem> podcastItemFinder;

    private ParseWatcher<Channel> listener;

    public PodcastChannelParser(ElementFinderFactory factory) {
        this.podcastItemFinder = factory.getListElementFinder(new PodcastItemParser(factory), parseWatcher);
    }

    private final ParseWatcher<PodcastItem> parseWatcher = new ParseWatcher<PodcastItem>() {
        @Override
        public void onParsed(PodcastItem item) {
            channelHolder.item.add(item);
        }
    };

    @Override
    public void parse(Element element, final ParseWatcher<Channel> listener) {
        this.listener = listener;
        element.setElementListener(channelParseListener);
        podcastItemFinder.find(element, TAG_ITEM);
    }

    private ChannelHolder channelHolder;
    private final ElementListener channelParseListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            channelHolder = new ChannelHolder();
        }

        @Override
        public void end() {
            listener.onParsed(channelHolder.asChannel());
        }
    };

    private static class ChannelHolder {
        private final List<PodcastItem> item = new ArrayList<PodcastItem>();

        public Channel asChannel() {
            return new Channel(item);
        }
    }
}