package com.novoda.demo.advanced.podcast;

import com.novoda.demo.Example;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class PodcastExample implements Example {

    private static ElementFinder<Channel> elementFinder;

    @Override
    public void execute() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();

        elementFinder = factory.getTypeFinder(new PodcastChannelParser(factory));

        Instigator instigator = new PodcastInstigator(elementFinder, finishWatcher);
        SimpleEasyXmlParser.parse(PodcastXmlHelper.SINGLE_PODCAST_ITEM, instigator);
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            System.out.println("Found : " + elementFinder.getResult());
            for (PodcastItem podcastItem : elementFinder.getResult().podcastItems) {
                System.out.println("Title : " + podcastItem.title);
                System.out.println("Author : " + podcastItem.author);
                System.out.println("Link : " + podcastItem.link);
                System.out.println("Itunes Duration : " + podcastItem.itunesDuration);
                System.out.println("Itunes Image : " + podcastItem.image);
                System.out.println("");
                System.out.println("");
            }
        }
    };

    public static class PodcastInstigator extends SimpleTagInstigator {

        public PodcastInstigator(ElementFinder<?> elementFinder, ParseFinishWatcher parseFinishWatcher) {
            super(elementFinder, "channel", parseFinishWatcher);
        }

        @Override
        public String getRootTag() {
            return "rss";
        }
    }
}
