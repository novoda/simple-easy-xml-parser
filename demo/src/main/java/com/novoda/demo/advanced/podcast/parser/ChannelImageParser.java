package com.novoda.demo.advanced.podcast.parser;

import com.novoda.demo.advanced.podcast.pojo.ChannelImage;
import com.novoda.sax.Element;
import com.novoda.sax.ElementListener;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseWatcher;
import com.novoda.sexp.parser.Parser;

import org.xml.sax.Attributes;

public class ChannelImageParser implements Parser<ChannelImage> {

    private static final String TAG_TITLE = "title";
    private static final String TAG_LINK = "link";
    private static final String TAG_URL = "url";
    private static final String TAG_WIDTH = "width";
    private static final String TAG_HEIGHT = "height";

    private final ElementFinder<String> titleFinder;
    private final ElementFinder<String> linkFinder;
    private final ElementFinder<String> urlFinder;
    private final ElementFinder<Integer> widthFinder;
    private final ElementFinder<Integer> heightFinder;

    private ParseWatcher<ChannelImage> listener;
    private ImageHolder imageHolder;

    public ChannelImageParser(ElementFinderFactory factory) {
        this.titleFinder = factory.getStringFinder();
        this.linkFinder = factory.getStringFinder();
        this.urlFinder = factory.getStringFinder();
        this.widthFinder = factory.getIntegerFinder();
        this.heightFinder = factory.getIntegerFinder();
    }

    @Override
    public void parse(Element element, ParseWatcher<ChannelImage> listener) {
        this.listener = listener;
        element.setElementListener(imageListener);
        titleFinder.find(element, TAG_TITLE);
        linkFinder.find(element, TAG_LINK);
        urlFinder.find(element, TAG_URL);
        widthFinder.find(element, TAG_WIDTH);
        heightFinder.find(element, TAG_HEIGHT);
    }

    private final ElementListener imageListener = new ElementListener() {
        @Override
        public void start(Attributes attributes) {
            imageHolder = new ImageHolder();
        }

        @Override
        public void end() {
            imageHolder.title = titleFinder.getResultOrThrow();
            imageHolder.link = linkFinder.getResultOrThrow();
            imageHolder.url = urlFinder.getResultOrThrow();
            imageHolder.width = widthFinder.getResultOrThrow();
            imageHolder.height = heightFinder.getResultOrThrow();

            listener.onParsed(imageHolder.asImage());
        }
    };

    private static class ImageHolder {

        public String title;
        public String link;
        public String url;
        public Integer width;
        public Integer height;

        public ChannelImage asImage() {
            return new ChannelImage(title, link, url, width, height);
        }
    }

}
