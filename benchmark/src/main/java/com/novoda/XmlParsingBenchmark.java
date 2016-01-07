package com.novoda;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.api.VmOptions;
import com.google.caliper.runner.CaliperMain;
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
        CaliperMain.main(XmlBenchmark.class, args);
    }

    @VmOptions("-XX:-TieredCompilation")
    public static class XmlBenchmark {

        private String xmlSmall;
        private String xmlMedium;

        @BeforeExperiment
        public void setUp() throws Exception {
            xmlSmall = loadResource("small.xml");
            xmlMedium = loadResource("medium.xml");
        }

        @Benchmark
        public void jackson_InputSmall() throws Exception {
            new JacksonSmallXmlBenchmark().parse(xmlSmall);
        }

        @Benchmark
        public void jackson_InputMedium() throws Exception {
            new JacksonMediumXmlBenchmark().parse(xmlMedium);
        }

        @Benchmark
        public void sexp_InputSmall() throws Exception {
            new SexpSmallXmlBenchmark().parse(xmlSmall);
        }

        @Benchmark
        public void sexp_InputMedium() throws Exception {
            new SexpMediumXmlBenchmark().parse(xmlMedium);
        }

        @Benchmark
        public void simpleframework_InputSmall() throws Exception {
            new SimpleFrameworkSmallXmlBenchmark().parse(xmlSmall);
        }

        @Benchmark
        public void simpleframework_InputMedium() throws Exception {
            new SimpleFrameworkMediumXmlBenchmark().parse(xmlMedium);
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
