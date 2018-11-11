package com.epam.xmlparsing.parser.jaxb;

import com.epam.xmlparsing.entity.*;
import com.epam.xmlparsing.exception.ParserException;
import com.epam.xmlparsing.parser.Parser;
import com.epam.xmlparsing.parser.factory.ParserFactory;
import com.epam.xmlparsing.parser.factory.ParserTypes;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

public class JaxbParserTest {
    private static final String INPUT_FILE = "src/test/resources/input.xml";
    private static final double DELTA = 0.001;

    @Test
    public void shouldParseWhenDataValid() throws ParserException {
        //given
        ParserFactory factory = new ParserFactory();
        Parser jaxbParser = factory.getParser(ParserTypes.JAXB);
        //when
        List<Drug> actual = jaxbParser.parse(INPUT_FILE);
        //then
        Assert.assertEquals(3,actual.size());
        Drug first = actual.get(0);
        Assert.assertEquals(first.getClass(),Drug.class);
        Assert.assertEquals("aspirin",first.getName());
        Assert.assertEquals("BelMed",first.getPharm());
        Assert.assertEquals(500,first.getDosage(),DELTA);
        Assert.assertEquals(10,first.getAmount());
        Assert.assertEquals(MedicalFulfillment.PILLS,first.getFulfillment());
        Certificate firstCertificate = first.getCertificate();
        Assert.assertNotNull(firstCertificate);
        Assert.assertEquals(42,firstCertificate.getId());
        Assert.assertEquals("Health Ministry",firstCertificate.getRegisteringOrganization());
        Assert.assertEquals(LocalDate.parse("1990-10-02"),firstCertificate.getIssueDate());
        Assert.assertEquals(LocalDate.parse("2020-10-02"),firstCertificate.getExpirationDate());

        Drug secondDrug = actual.get(1);
        Assert.assertEquals(secondDrug.getClass(),Vitamin.class);
        Vitamin second = (Vitamin)secondDrug;
        Assert.assertEquals("vitamin C",second.getName());
        Assert.assertEquals("BelMed",second.getPharm());
        Assert.assertEquals(500,second.getDosage(),DELTA);
        Assert.assertEquals(30,second.getAmount());
        Assert.assertEquals(MedicalFulfillment.PILLS,second.getFulfillment());
        Certificate secondCertificate = second.getCertificate();
        Assert.assertNotNull(secondCertificate);
        Assert.assertEquals(425,secondCertificate.getId());
        Assert.assertEquals("Health Ministry",firstCertificate.getRegisteringOrganization());
        Assert.assertEquals(LocalDate.parse("1990-10-02"),firstCertificate.getIssueDate());
        Assert.assertEquals(LocalDate.parse("2020-10-02"),firstCertificate.getExpirationDate());
        Assert.assertEquals(60,second.getDailyNeed());

        Drug thirdDrug = actual.get(2);
        Assert.assertEquals(thirdDrug.getClass(),PrescriptionalDrug.class);
        PrescriptionalDrug third = (PrescriptionalDrug)thirdDrug;
        Assert.assertEquals("azithromycin",third.getName());
        Assert.assertEquals("BelMed",third.getPharm());
        Assert.assertEquals(500,third.getDosage(),DELTA);
        Assert.assertEquals(3,third.getAmount());
        Assert.assertEquals(MedicalFulfillment.PILLS,third.getFulfillment());
        Certificate thirdCertificate = third.getCertificate();
        Assert.assertNotNull(thirdCertificate);
        Assert.assertEquals(346,thirdCertificate.getId());
        Assert.assertEquals("Health Ministry",thirdCertificate.getRegisteringOrganization());
        Assert.assertEquals(LocalDate.parse("1990-10-02"),thirdCertificate.getIssueDate());
        Assert.assertEquals(LocalDate.parse("2020-10-02"),thirdCertificate.getExpirationDate());
        Assert.assertFalse(third.isAddictive());
        Assert.assertFalse(third.isNarcotic());
    }


}
