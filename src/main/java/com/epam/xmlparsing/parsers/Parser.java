package com.epam.xmlparsing.parsers;

import com.epam.xmlparsing.entity.Drug;
import com.epam.xmlparsing.exceptions.ParserException;

import java.util.List;

public interface Parser {
    List<Drug> parse(String path)throws ParserException;
}

