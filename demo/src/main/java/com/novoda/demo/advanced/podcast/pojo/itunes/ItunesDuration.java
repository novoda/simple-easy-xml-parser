package com.novoda.demo.advanced.podcast.pojo.itunes;

public class ItunesDuration {

    private final String durationRaw;

    public ItunesDuration(String durationRaw) {
        this.durationRaw = durationRaw;
    }

    @Override
    public String toString() {
        return durationRaw;
    }
}
