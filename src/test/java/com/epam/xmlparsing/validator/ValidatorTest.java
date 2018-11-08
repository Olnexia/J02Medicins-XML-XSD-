package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.exception.ValidatorException;
import org.junit.Test;

public class ValidatorTest {
    private static final String XML_FILE_PATH = "src/test/resources/input.xml";
    private static final String SCHEMA_FILE_PATH = "src/main/resources/schema.xsd";

    @Test
    public void shouldValidateWhenFileDataValid() throws ValidatorException {
        //given
        XmlValidator validator = new XmlValidator();
        //when
        validator.validate(XML_FILE_PATH,SCHEMA_FILE_PATH);
        //then
    }
}
