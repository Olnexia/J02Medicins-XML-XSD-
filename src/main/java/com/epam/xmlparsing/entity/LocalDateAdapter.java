package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v,formatter);
    }

    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}