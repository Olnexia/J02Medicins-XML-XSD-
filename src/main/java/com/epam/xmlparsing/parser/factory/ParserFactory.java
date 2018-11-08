package com.epam.xmlparsing.parser.factory;

import com.epam.xmlparsing.parser.Parser;

public class ParserFactory {
    public Parser getParser(ParserTypes parserType){
        return parserType.getInstance();
    }
}
