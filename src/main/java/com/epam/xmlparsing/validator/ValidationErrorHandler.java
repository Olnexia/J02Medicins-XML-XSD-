package com.epam.xmlparsing.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class ValidationErrorHandler implements ErrorHandler {
    private static final Logger logger = LogManager.getLogger(ValidationErrorHandler.class);

    @Override
    public void warning(SAXParseException exception) {
        logger.warn(exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        logger.error(exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) {
        logger.fatal(exception.getMessage());
    }
}
