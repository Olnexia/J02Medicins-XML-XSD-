package com.epam.xmlparsing.parser.saxparser;

import com.epam.xmlparsing.entity.Drug;
import com.epam.xmlparsing.exception.ParserException;
import com.epam.xmlparsing.parser.Parser;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {
    private MedicinsHandler handler;
    private XMLReader reader;

    public SaxParser() throws ParserException{
        handler = new MedicinsHandler();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            factory.setNamespaceAware(true);
            SAXParser parser = factory.newSAXParser();
            reader = parser.getXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            throw new ParserException("An exception occurred while parsing the file",e);
        } catch(ParserConfigurationException e) {
            throw new ParserException("A configuration exception occurred while parsing the file",e);
        }
    }

    @Override
    public List <Drug> parse(String path) throws ParserException {
        try {
            reader.parse(path);
        } catch (SAXException e) {
            throw new ParserException("An exception occurred while parsing the file",e);
        } catch (IOException e) {
            throw new ParserException("An exception occurred with the parsing file",e);
        }
        return handler.getMedicins();
    }
}
