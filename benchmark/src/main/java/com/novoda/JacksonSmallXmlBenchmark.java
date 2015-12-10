package com.novoda;

import com.fasterxml.jackson.xml.XmlMapper;

public class JacksonSmallXmlBenchmark {

    public void parse(String xml) throws Exception {
        XmlMapper mapper = new XmlMapper();
        Employee employee = mapper.readValue(xml, Employee.class);
        System.out.println(getClass().getSimpleName() + " " + employee.name);
    }

    public static class Employee {
        public String name;
    }
}
