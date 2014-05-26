SimpleEasyXmlParser
===================

A simple XML parser based upon Android sax parser but written in pure Java. SEXP gives callbacks for all parsing events and being written in pure java allows faster and more comprehensive testability.

Example Useage
===============


For now examples of use can be found here:


[SEXP Java Demo's](https://github.com/novoda/SimpleEasyXmlParser/tree/master/demo/src/main/java/com/novoda/demo)

[SEXP Android Demo's](https://github.com/novoda/SimpleEasyXmlParser/tree/master/demo/src/main/java/com/novoda/demoAndroid)

Adding to your project
======

Gradle
-
````groovy
repositories {
    maven {
        url 'http://ci.novoda.com/maven/releases/'
    }
}
`````

````groovy
dependencies {
    compile 'com.novoda.sexp:core:1.0.2-alpha'
}
````

![SEXP](/professor_sexp.jpg)
