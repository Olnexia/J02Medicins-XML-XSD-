package com.epam.xmlparsing.parser;

import com.epam.xmlparsing.entity.Drug;
import com.epam.xmlparsing.exception.ParserException;
import java.util.List;

public interface Parser {
    List<Drug> parse(String path)throws ParserException;
}

