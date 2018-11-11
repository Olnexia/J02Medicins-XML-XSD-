package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.exception.ValidatorException;
import com.epam.xmlparsing.validator.errorhandler.ValidationErrorHandler;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {
    public void validate(String xmlFilePath,String schemaPath) throws ValidatorException{
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaPath);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilePath);
            validator.setErrorHandler(new ValidationErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            throw new ValidatorException(e.getMessage(),e);
        }
    }
}
