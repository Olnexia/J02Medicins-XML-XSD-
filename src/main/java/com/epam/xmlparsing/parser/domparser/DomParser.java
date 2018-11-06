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
    private DocumentBuilder documentBuilder;

    public DomParser() throws ParserException{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        try{
            documentBuilder = factory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            throw new ParserException("Exception DOM parser configuration",e);
        }
    }

    public List<Drug> parse(String path) throws ParserException {
        List<Drug> drugList = new ArrayList <>();
        Document document;
        try{
            document = documentBuilder.parse(path);
            Element root = document.getDocumentElement();
            NodeList rootChildNodesList = root.getChildNodes();
            for( int i =0; i<rootChildNodesList.getLength();i++){
                Node node = rootChildNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element currentElement = (Element) node.getChildNodes();
                    Drug drug = parseRootChild(currentElement);
                    drugList.add(drug);
                }
            }
        } catch (SAXException e) {
            throw new ParserException("An exception occurred while parsing the file",e);
        } catch (IOException e) {
            throw new ParserException("An exception occurred with the parsing file",e);
        }
        return drugList;
    }

    private Drug parseRootChild(Element drugElement){
        Drug drug = null;
        String elementName = drugElement.getTagName();
        switch (elementName) {
            case "drug":
                drug = new Drug();
                break;
            case "xsi:presctiptional-drug":
                drug = new PrescriptionalDrug();
                boolean addictive = Boolean.parseBoolean(getElementTextContent(drugElement,"addictive"));
                ((PrescriptionalDrug) drug).setAddictive(addictive);
                boolean narcotical = Boolean.parseBoolean(getElementTextContent(drugElement,"narcotical"));
                ((PrescriptionalDrug) drug).setNarcotical(narcotical);
                break;
            case "xsi:vitamin":
                drug = new Vitamin();
                Integer dailyNeed = Integer.parseInt(getElementTextContent(drugElement,"daily-need"));
                ((Vitamin) drug).setDailyNeed(dailyNeed);
                break;
                default:
                 //excetion
        }
        drug.setName(drugElement.getAttribute("name"));
        drug.setPharm(getElementTextContent(drugElement,"pharm"));
        MedicalFulfillment fulfillment = MedicalFulfillment.valueOf(getElementTextContent(drugElement,"fulfillment"));
        drug.setFulfillment(fulfillment);
        Integer amount = Integer.parseInt(getElementTextContent(drugElement,"amount"));
        drug.setAmount(amount);
        double dosage = Double.parseDouble(getElementTextContent(drugElement,"dosage"));
        drug.setDosage(dosage);
        BigDecimal price = new BigDecimal(getElementTextContent(drugElement,"price"));
        drug.setPrice(price);
        Certificate certificate = drug.getCertificate();
        Element certificateElement = (Element) drugElement.getElementsByTagName("certificate").item(0);
        Long id = Long.parseLong(getElementTextContent(certificateElement,"id"));
        certificate.setId(id);
        String issueDateStr= getElementTextContent(certificateElement,"issue-date");
        String [] issueDate = issueDateStr.split("\\.");
        certificate.setIssueDate(LocalDate.of(Integer.parseInt(issueDate[2]),Integer.parseInt(issueDate[1]),Integer.parseInt(issueDate[0])));
        String expirationDateStr= getElementTextContent(certificateElement,"expiration-date");
        String [] expirationDate = expirationDateStr.split("\\.");
        certificate.setExpirationDate(LocalDate.of(Integer.parseInt(expirationDate[2]),Integer.parseInt(expirationDate[1]),Integer.parseInt(expirationDate[0])));
        certificate.setRegisteringOrganization(getElementTextContent(certificateElement,"registering-organization"));
        return drug;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
