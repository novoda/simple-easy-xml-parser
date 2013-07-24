package com.novoda.demo.advanced.podcast.pojo;

public class Author {

    private final String author;

    public Author(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return author;
    }
}
