package com.novoda;

import com.google.caliper.Runner;
import com.google.caliper.SimpleBenchmark;
import com.novoda.jackson.medium.JacksonMediumXmlBenchmark;
import com.novoda.jackson.small.JacksonSmallXmlBenchmark;
import com.novoda.sexp.medium.SexpMediumXmlBenchmark;
import com.novoda.sexp.small.SexpSmallXmlBenchmark;
import com.novoda.simple.medium.SimpleFrameworkMediumXmlBenchmark;
import com.novoda.simple.small.SimpleFrameworkSmallXmlBenchmark;

import java.io.IOException;
import java.io.InputStream;

public class XmlParsingBenchmark {

    public static void main(String[] args) {
        Runner.main(XmlBenchmark.class, args);
    }

    public static class XmlBenchmark extends SimpleBenchmark {

        private String xmlSmall;
        private String xmlMedium;

        @Override
        public void setUp() throws Exception {
            xmlSmall = loadResource("small.xml");
            xmlMedium = loadResource("medium.xml");
        }

        public void time_jackson_InputSmall(int reps) throws Exception {
            for (int i = 0; i < reps; i++) {
                new JacksonSmallXmlBenchmark().parse(xmlSmall);
            }
        }

        public void time_jackson_InputMedium(int reps) throws Exception {
            for (int i = 0; i < reps; i++) {
                new JacksonMediumXmlBenchmark().parse(xmlMedium);
            }
        }

        public void time_sexp_InputSmall(int reps) throws Exception {
            for (int i = 0; i < reps; i++) {
                new SexpSmallXmlBenchmark().parse(xmlSmall);
            }
        }

        public void time_sexp_InputMedium(int reps) throws Exception {
            for (int i = 0; i < reps; i++) {
                new SexpMediumXmlBenchmark().parse(xmlMedium);
            }
        }

        public void time_simpleframework_InputSmall(int reps) throws Exception {
            for (int i = 0; i < reps; i++) {
                new SimpleFrameworkSmallXmlBenchmark().parse(xmlSmall);
            }
        }

        public void time_simpleframework_InputMedium(int reps) throws Exception {
            for (int i = 0; i < reps; i++) {
                new SimpleFrameworkMediumXmlBenchmark().parse(xmlMedium);
            }
        }

        private String loadResource(String resourceName) throws IOException {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            try (InputStream stream = classLoader.getResourceAsStream(resourceName)) {
                return convertStreamToString(stream);
            }
        }

        private static String convertStreamToString(java.io.InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }
    }
}
