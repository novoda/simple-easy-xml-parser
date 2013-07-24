package com.novoda.demo.advanced.podcast;

import com.novoda.demo.advanced.podcast.pojo.Author;
import com.novoda.demo.advanced.podcast.pojo.Link;
import com.novoda.demo.advanced.podcast.pojo.Title;
import com.novoda.demo.advanced.podcast.pojo.itunes.ItunesDuration;

public class PodcastItem {

    public final Title title;
    public final Author author;
    public final Link image;
    public final Link link;
    public final ItunesDuration itunesDuration;

    public PodcastItem(Title title, Author author, Link image, Link link, ItunesDuration itunesDuration) {
        this.title = title;
        this.author = author;
        this.image = image;
        this.link = link;
        this.itunesDuration = itunesDuration;
    }
}
