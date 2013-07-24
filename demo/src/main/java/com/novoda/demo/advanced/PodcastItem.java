package com.novoda.demo.advanced;

import com.novoda.demo.advanced.pojo.Author;
import com.novoda.demo.advanced.pojo.Link;
import com.novoda.demo.advanced.pojo.Title;

public class PodcastItem {

    public final Title title;
    public final Author author;
    public final Link link;

    public PodcastItem(Title title, Author author, Link link) {
        this.title = title;
        this.author = author;
        this.link = link;
    }
}
