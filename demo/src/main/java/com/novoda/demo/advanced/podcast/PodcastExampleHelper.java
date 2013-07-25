package com.novoda.demo.advanced.podcast;

import com.novoda.demo.advanced.podcast.pojo.Channel;
import com.novoda.demo.advanced.podcast.pojo.ChannelImage;
import com.novoda.demo.advanced.podcast.pojo.PodcastItem;

import java.util.List;

public class PodcastExampleHelper {

    //language=XML
    public static final String SINGLE_PODCAST_ITEM =
            "<rss xmlns:itunes=\"http://www.itunes.com/dtds/podcast-1.0.dtd\" xmlns:atom=\"http://www.w3.org/2005/Atom\" version=\"2.0\">" +
                "<channel>" +
                    "<title>CNET UK Podcast</title>" +
                    "<link>http://crave.cnet.co.uk/podcast/</link>" +
                    "<image>" +
                        "<url>http://www.cnet.co.uk/images/rss/logo-cnet.jpg</url>" +
                        "<link>http://crave.cnet.co.uk/podcast/</link>" +
                        "<title>CNET UK Podcast</title>" +
                        "<width>88</width>" +
                        "<height>56</height>" +
                    "</image>" +
                    "<item>" +
                        "<author>podcast@cnet.co.uk (CNET UK)</author>" +
                        "<title>" +
                            "New BBC HD channels and the future of TV in Podcast 348 " +
                        "</title>" +
                        "<link>" +
                            "http://crave.cnet.co.uk/podcast/new-bbc-hd-channels-and-the-future-of-tv-in-podcast-348-50011743/ " +
                        "</link>" +
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
                    "<item>" +
                        "<author>podcast@cnet.co.uk (CNET UK)</author>" +
                        "<title>Free stuff for your phone in Podcast 346</title>" +
                        "<link>" +
                            "http://crave.cnet.co.uk/podcast/free-stuff-for-your-phone-in-podcast-346-50011642/" +
                        "</link>" +
                        "<pubDate>Thu, 04 Jul 2013 12:05:07 +0100</pubDate>" +
                        "<enclosure url=\"http://www.podtrac.com/pts/redirect.mp3?http://cdn-media.cbsinteractive.co.uk/cnetcouk/podcasts/crave/cnetuk_podcast_346.mp3\" type=\"audio/mpeg\"/>" +
                        "<guid isPermaLink=\"false\">cnetuk/podcast/50011642</guid>" +
                        "<itunes:author>Luke Westaway, Andrew Hoyle, Rich Trenholm</itunes:author>" +
                        "<itunes:duration>00:37:43</itunes:duration>" +
                        "<itunes:explicit>no</itunes:explicit>" +
                        "<itunes:image href=\"http://cdn-static.cnet.co.uk/i/c/blg/cat/podcasting/cp346.jpg\"/>" +
                        "<itunes:keywords/>" +
                        "<itunes:subtitle>Free stuff for your phone in Podcast 346</itunes:subtitle>" +
                        "<itunes:summary>" +
                            "Bag free stuff from your phone network as we explore the world of extras, bolt-ons and Freebeez in Podcast 346." +
                        "</itunes:summary>" +
                    "</item>" +
                "</channel>" +
            "</rss>";

    public static void printChannelDetails(Channel channel) {
        System.out.println("Channel Title : " + channel.title);
        System.out.println("Channel Link : " + channel.link);
    }

    public static void printChannelImage(ChannelImage image) {
        System.out.println("Channel Image Title : " + image.title);
        System.out.println("Channel Image Url : " + image.url);
    }

    public static void printAllPodcastItems(List<PodcastItem> podcastItems) {
        for (PodcastItem podcastItem : podcastItems) {
            System.out.println("Title : " + podcastItem.title);
            System.out.println("Author : " + podcastItem.author);
            System.out.println("Link : " + podcastItem.link);
            System.out.println("Itunes Duration : " + podcastItem.itunesDuration);
            System.out.println("Itunes Image : " + podcastItem.image);
            printSpace();
        }
    }

    public static void printSpace() {
        System.out.println("");
        System.out.println("");
    }

}
