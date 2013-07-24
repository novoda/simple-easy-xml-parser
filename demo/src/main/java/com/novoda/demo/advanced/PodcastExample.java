package com.novoda.demo.advanced;

import com.novoda.demo.Example;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class PodcastExample implements Example {

    //language=XML
    private static final String XML =
            "<rss xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\" xmlns:atom=\"http://www.w3.org/2005/Atom\" version=\"2.0\">" +
                "<item>" +
                    "<author>podcast@cnet.co.uk (CNET UK)</author>" +
                    "<title> " +
                        "New BBC HD channels and the future of TV in Podcast 348 " +
                    "</title> " +
                    "<link> " +
                        "http://crave.cnet.co.uk/podcast/new-bbc-hd-channels-and-the-future-of-tv-in-podcast-348-50011743/ " +
                    "</link> " +
                    "<pubDate>Thu, 18 Jul 2013 12:07:02 +0100</pubDate> " +
                    "<enclosure url=\"http://www.podtrac.com/pts/redirect.mp3?http://cdn-media.cbsinteractive.co.uk/cnetcouk/podcasts/crave/cnetuk_podcast_348.mp3\" type=\"audio/mpeg\"/> " +
                    "<guid isPermaLink=\"false\">cnetuk/podcast/50011743</guid> " +
                    "<itunes:author>Jason Jenkins, Andrew Hoyle, Rich Trenholm</itunes:author> " +
                    "<itunes:duration>00:42:50</itunes:duration> " +
                    "<itunes:explicit>no</itunes:explicit> " +
                    "<itunes:image href=\"http://cdn-static.cnet.co.uk/i/c/blg/cat/podcasting/cp348.jpg\"/> " +
                    "<itunes:keywords/> " +
                    "<itunes:subtitle> " +
                        "New BBC HD channels and the future of TV in Podcast 348 " +
                    "</itunes:subtitle> " +
                    "<itunes:summary> " +
                        "As the BBC goes HD, Netflix reinvents the TV series, and BT and Sky square off, what's the future of television -- and is Apple in it? " +
                    "</itunes:summary> " +
                "</item>" +
            "</rss>";

    private static ElementFinder<PodcastItem> elementFinder;

    @Override
    public void execute() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();

        elementFinder = factory.getTypeFinder(new PodcastItemParser(factory));

        Instigator instigator = new PodcastInstigator(elementFinder, finishWatcher);
        SimpleEasyXmlParser.parse(XML, instigator);
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            System.out.println("Found : " + elementFinder.getResult());
            System.out.println("Title : " + elementFinder.getResult().title);
            System.out.println("Author : " + elementFinder.getResult().author);
            System.out.println("Link : " + elementFinder.getResult().link);
        }
    };

    public static class PodcastInstigator extends SimpleTagInstigator {

        public PodcastInstigator(ElementFinder<?> elementFinder, ParseFinishWatcher parseFinishWatcher) {
            super(elementFinder, "item", parseFinishWatcher);
        }

        @Override
        public String getRootTag() {
            return "rss";
        }
    }
}
