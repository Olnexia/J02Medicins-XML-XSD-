package com.epam.xmlparsing.parser.sax;

import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.entity.Drug;
import com.epam.xmlparsing.exception.ParserException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {
    @Override
    public List <Drug> parse(String path) throws ParserException {
        MedicinsHandler handler = new MedicinsHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        try {
            SAXParser parser = factory.newSAXParser();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(handler);
            reader.parse(path);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new ParserException(e.getMessage(),e);
        }
        return handler.getMedicins();
    }
}
