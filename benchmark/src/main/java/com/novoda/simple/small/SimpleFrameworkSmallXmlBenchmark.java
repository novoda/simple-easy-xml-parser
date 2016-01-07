package com.novoda.simple.small;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class SimpleFrameworkSmallXmlBenchmark {

    public void parse(String xml) throws Exception {
        Serializer serializer = new Persister();
        Employee employee = serializer.read(Employee.class, xml);
        System.out.println(getClass().getSimpleName() + " " + employee.name);
    }

    @Root(name = "employee", strict = false)
    public static class Employee {
        @Element(required = true)
        public String name;
    }
}
