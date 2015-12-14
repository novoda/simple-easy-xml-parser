package com.novoda.sexp.medium;

import com.google.caliper.BeforeExperiment;
import com.google.caliper.Benchmark;
import com.google.caliper.api.VmOptions;
import com.google.caliper.runner.CaliperMain;
import com.novoda.jackson.medium.JacksonMediumXmlBenchmark;
import com.novoda.jackson.small.JacksonSmallXmlBenchmark;
import com.novoda.sexp.small.SexpSmallXmlBenchmark;
import com.novoda.simple.medium.SimpleFrameworkMediumXmlBenchmark;
import com.novoda.simple.small.SimpleFrameworkSmallXmlBenchmark;

import java.io.InputStream;

public class XmlParsingBenchmark {

    public static void main(String[] args) {
        CaliperMain.main(MediumXmlBenchmark.class, args);
    }

    @VmOptions("-XX:-TieredCompilation")
    public static class SmallXmlBenchmark {

        private static final String XML = "<employee><name>Paul</name></employee>";

        @Benchmark
        public void sexp() throws Exception {
            new SexpSmallXmlBenchmark().parse(XML);
        }

        @Benchmark
        public void jackson() throws Exception {
            new JacksonSmallXmlBenchmark().parse(XML);
        }

        @Benchmark
        public void simpleframework() throws Exception {
            new SimpleFrameworkSmallXmlBenchmark().parse(XML);
        }
    }

    @VmOptions("-XX:-TieredCompilation")
    public static class MediumXmlBenchmark {

        private String xml;

        @BeforeExperiment
        public void setUp() throws Exception {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classloader.getResourceAsStream("medium.xml");
            xml = convertStreamToString(stream);
            stream.close();
        }

        static String convertStreamToString(java.io.InputStream is) {
            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }

        @Benchmark
        public void sexp() throws Exception {
            new SexpMediumXmlBenchmark().parse(xml);
        }

        @Benchmark
        public void jackson() throws Exception {
            new JacksonMediumXmlBenchmark().parse(xml);
        }

        @Benchmark
        public void simpleframework() throws Exception {
            new SimpleFrameworkMediumXmlBenchmark().parse(xml);
        }
    }
}
