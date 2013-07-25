package com.novoda.demo.advanced.podcast;

import java.util.List;

class Channel {

    public final List<PodcastItem> podcastItems;

    public Channel(List<PodcastItem> item) {
        this.podcastItems = item;
    }
}
