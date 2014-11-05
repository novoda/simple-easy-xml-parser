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

```groovy
repositories {
    maven {
        url 'http://ci.novoda.com/maven/releases/'
    }
}
```

```groovy
dependencies {
    compile 'com.novoda.sexp:core:1.0.2-alpha'
}
```

License
-------

    (c) Copyright 2014 Novoda

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

![SEXP](/professor_sexp.jpg)
