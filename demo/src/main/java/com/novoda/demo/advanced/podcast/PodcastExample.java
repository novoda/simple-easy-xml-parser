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
            System.out.println("Title : " + elementFinder.getResult().item.title);
            System.out.println("Author : " + elementFinder.getResult().item.author);
            System.out.println("Link : " + elementFinder.getResult().item.link);
            System.out.println("Itunes Duration : " + elementFinder.getResult().item.itunesDuration);
            System.out.println("Itunes Image : " + elementFinder.getResult().item.image);
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
