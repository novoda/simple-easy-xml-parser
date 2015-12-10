package com.novoda;

import com.google.caliper.Benchmark;
import com.google.caliper.api.VmOptions;
import com.google.caliper.runner.CaliperMain;

public class XmlParsingBenchmark {

    public static void main(String[] args) {
        CaliperMain.main(SmallXmlBenchmark.class, args);
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

}
