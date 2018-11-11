package com.epam.xmlparsing.validator;

import com.epam.xmlparsing.exception.ValidatorException;
import org.junit.Test;

public class ValidatorTest {
    private static final String XML_FILE_PATH = "src/test/resources/input.xml";
    private static final String SCHEMA_FILE_PATH = "src/main/resources/schema.xsd";
    private static final String NONEXISTENT_FILE_PATH = "sec/test/resources/non.xml";


    //The test is considered passed if the validator has worked without exceptions.
    // All input file errors are logged by ValidationErrorHandler class
    @Test
    public void shouldValidateWhenFileDataValid() throws ValidatorException {
        //given
        XmlValidator validator = new XmlValidator();
        //when
        validator.validate(XML_FILE_PATH,SCHEMA_FILE_PATH);
        //then
    }

    @Test(expected = ValidatorException.class)
    public void shouldThrowValidatorExceptionWhenWrongFilePath() throws ValidatorException {
        //given
        XmlValidator validator = new XmlValidator();
        //when
        validator.validate(NONEXISTENT_FILE_PATH,SCHEMA_FILE_PATH);
        //then
    }
}
