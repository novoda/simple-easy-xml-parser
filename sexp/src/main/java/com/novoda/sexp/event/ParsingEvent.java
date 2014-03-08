package com.novoda.sexp.event;

/**
 * Created by Ianic on 3/8/14.
 */
public class ParsingEvent {
    private int mEventId;

    public ParsingEvent(int eventId){
        mEventId = eventId;
    }

    public int getEventId() {
        return mEventId;
    }
}
