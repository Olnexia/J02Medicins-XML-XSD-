package com.epam.xmlparsing.parser.jaxbparser;

import com.epam.xmlparsing.entity.Drug;
import com.epam.xmlparsing.entity.Medicins;
import com.epam.xmlparsing.exception.ParserException;
import com.epam.xmlparsing.parser.Parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {
    public List<Drug> parse(String path) throws ParserException {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(Medicins.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Medicins medicins = (Medicins) jaxbUnmarshaller.unmarshal(file);
            return medicins.getDrugs();
        }catch (JAXBException ex){
            throw new ParserException(ex.getMessage(),ex);
        }
    }
}
