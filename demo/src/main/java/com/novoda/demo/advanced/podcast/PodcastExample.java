package com.novoda.demo.advanced.podcast;

import com.novoda.demo.Example;
import com.novoda.demo.advanced.podcast.parser.PodcastChannelParser;
import com.novoda.demo.advanced.podcast.pojo.Channel;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

import static com.novoda.demo.advanced.podcast.PodcastExampleHelper.*;

public class PodcastExample implements Example {

    private static ElementFinder<Channel> elementFinder;

    @Override
    public void execute() {
        ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();

        elementFinder = factory.getTypeFinder(new PodcastChannelParser(factory));

        Instigator instigator = new PodcastInstigator(elementFinder, finishWatcher);
        SimpleEasyXmlParser.parse(PodcastExampleHelper.SINGLE_PODCAST_ITEM, instigator);
    }

    private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
        @Override
        public void onFinish() {
            Channel channel = elementFinder.getResultOrThrow();

            printChannelDetails(channel);
            printChannelImage(channel.image);

            printSpace();

            printAllPodcastItems(channel.podcastItems);
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
