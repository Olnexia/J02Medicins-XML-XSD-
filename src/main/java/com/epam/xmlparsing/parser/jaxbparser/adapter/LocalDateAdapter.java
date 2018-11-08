package com.epam.xmlparsing.parser.jaxbparser.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public LocalDate unmarshal(String v){
        return LocalDate.parse(v,formatter);
    }

    public String marshal(LocalDate v){
        return v.toString();
    }
}
