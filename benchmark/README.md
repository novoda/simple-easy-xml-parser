Attempting to find out which dahm XML parser to use!

Run the main method inside `XmlParsingBenchmark.java` and wait for the results.

At the moment we test:

- A simple XML input of two tags

- A medium XML input simulating a feed composed of 100 entries of medium complexity.

Future:

- More complex tests and perhaps some discussive text about the API needed

Results:

- For the medium XML input SEXP is performing 1.7x faster than [Jackson](https://github.com/FasterXML/jackson-dataformat-xml) and 2.5x faster than [Simple-Framework](http://simple.sourceforge.net/). [Details](https://github.com/novoda/simple-easy-xml-parser/tree/master/benchmark)


We rely on https://github.com/google/caliper/wiki/ to do the hard work of twiddling the nobs.
