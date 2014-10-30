Droidcon challenge
==

Add a unit test for the chance to win a **hudl2**!

Each day of droidcon we'll announce a repo that's in need of some test love, to enter the competition you will need to follow these rules :

1. Fork the repo on github.
2. Add **one** unique passing unit test to the **test** directory **without** touching the production code.
3. Make a pull request against our repo on github (with your best gif)

**At the end of each day one winner will be chosen at random from the pull requests made that day**

Useful stuff:

- Follow **[@novoda](http://twitter.com/novoda)** to stay in the loop
- Using android-studio open the root build.gradle to import the project. Magic, no setup! *unless you're on windows*...
- Run the all the tests from the command line with `./gradlew test`


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
