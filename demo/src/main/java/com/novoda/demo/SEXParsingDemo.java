package com.novoda.demo;

import com.novoda.demo.advanced.PodcastExample;

public class SEXParsingDemo {

    public static void main(String[] args) {
//        executeAndLog(new OneTagExample());
//        executeAndLog(new SimpleExample());
//        executeAndLog(new TeamExample());
        executeAndLog(new PodcastExample());
    }

    public static void executeAndLog(Example example) {
        System.out.println(example.getClass().getSimpleName());
        example.execute();
        System.out.println("___________________________");
    }

}
