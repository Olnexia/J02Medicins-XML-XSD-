package com.epam.xmlparsing.parser.domparser;

import com.epam.xmlparsing.entity.*;
import com.epam.xmlparsing.exception.ParserException;
import com.epam.xmlparsing.parser.Parser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {
    private static final int YEAR_INDEX = 2;
    private static final int MONTH_INDEX = 1;
    private static final int DAY_INDEX = 0;

    public List<Drug> parse(String path) throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        List<Drug> drugList = new ArrayList <>();
        try{
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            Document document = documentBuilder.parse(path);
            Element root = document.getDocumentElement();
            NodeList rootChildNodesList = root.getChildNodes();
            for( int i = 0; i<rootChildNodesList.getLength(); i++){
                Node node = rootChildNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element currentElement = (Element) node.getChildNodes();
                    Drug drug = parseElement(currentElement);
                    drugList.add(drug);
                }
            }
        } catch (SAXException | IOException | ParserConfigurationException |IllegalArgumentException e) {
            throw new ParserException(e.getMessage(),e);
        }
        return drugList;
    }

    private Drug parseElement(Element drugElement){
        Drug drug;
        String elementName = drugElement.getTagName();
        switch (elementName) {
            case "drug":
                drug = new Drug();
                break;
            case "prescriptional-drug":
                drug = new PrescriptionalDrug();
                boolean addictive = Boolean.parseBoolean(getElementTextContent(drugElement,"addictive"));
                ((PrescriptionalDrug) drug).setAddictive(addictive);
                boolean narcotic = Boolean.parseBoolean(getElementTextContent(drugElement,"narcotic"));
                ((PrescriptionalDrug) drug).setNarcotic(narcotic);
                break;
            case "vitamin":
                drug = new Vitamin();
                int dailyNeed = Integer.parseInt(getElementTextContent(drugElement,"daily-need"));
                ((Vitamin) drug).setDailyNeed(dailyNeed);
                break;
                default:
                 throw new IllegalArgumentException("Wrong element. One of {drug,prescriptional-drug,vitamin} are expected");
        }
        drug.setName(drugElement.getAttribute("name"));
        drug.setPharm(getElementTextContent(drugElement,"pharm"));

        MedicalFulfillment fulfillment = MedicalFulfillment.valueOf(getElementTextContent(drugElement,"fulfillment").toUpperCase());
        drug.setFulfillment(fulfillment);

        int amount = Integer.parseInt(getElementTextContent(drugElement,"amount"));
        drug.setAmount(amount);

        double dosage = Double.parseDouble(getElementTextContent(drugElement,"dosage"));
        drug.setDosage(dosage);

        BigDecimal price = new BigDecimal(getElementTextContent(drugElement,"price"));
        drug.setPrice(price);

        Certificate certificate = drug.getCertificate();
        Element certificateElement = (Element) drugElement.getElementsByTagName("certificate").item(0);

        long id = Long.parseLong(getElementTextContent(certificateElement,"id"));
        certificate.setId(id);

        String issueDateStr= getElementTextContent(certificateElement,"issue-date");
        String[] issueDate = issueDateStr.split("\\.");
        certificate.setIssueDate(LocalDate.of(Integer.parseInt(issueDate[YEAR_INDEX]),
                                                Integer.parseInt(issueDate[MONTH_INDEX]),
                                                Integer.parseInt(issueDate[DAY_INDEX])));

        String expirationDateStr= getElementTextContent(certificateElement,"expiration-date");
        String[] expirationDate = expirationDateStr.split("\\.");
        certificate.setExpirationDate(LocalDate.of(Integer.parseInt(expirationDate[YEAR_INDEX]),
                                                    Integer.parseInt(expirationDate[MONTH_INDEX]),
                                                    Integer.parseInt(expirationDate[DAY_INDEX])));

        certificate.setRegisteringOrganization(getElementTextContent(certificateElement,"registering-organization"));
        return drug;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
