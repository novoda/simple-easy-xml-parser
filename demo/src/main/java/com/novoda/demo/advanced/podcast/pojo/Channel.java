package com.novoda.demo.advanced.podcast.pojo;

import java.util.List;

public class Channel {

    public final Title title;
    public final Link link;
    public final ChannelImage image;
    public final List<PodcastItem> podcastItems;

    public Channel(Title title, Link link, ChannelImage image, List<PodcastItem> item) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.podcastItems = item;
    }
}
