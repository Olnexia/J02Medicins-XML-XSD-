package com.epam.xmlparsing.parser.factory;

import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.dom.DomParser;
import com.epam.xmlparsing.parser.jaxb.JaxbParser;
import com.epam.xmlparsing.parser.sax.SaxParser;

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
