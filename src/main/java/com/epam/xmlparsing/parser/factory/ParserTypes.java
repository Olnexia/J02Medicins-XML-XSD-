package com.epam.xmlparsing.parser.factory;

import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.domparser.DomParser;
import com.epam.xmlparsing.parser.jaxbparser.JaxbParser;
import com.epam.xmlparsing.parser.saxparser.SaxParser;

public enum ParserTypes {
    SAX {
        @Override
        public SaxParser getInstance() {
            return new SaxParser ();
        }
    },
    DOM {
        @Override
        public DomParser getInstance() {
            return new DomParser();
        }
    },
    JAXB {
        @Override
        public JaxbParser getInstance(){
            return new JaxbParser();
        }
    };

    public abstract Parser getInstance();
}
