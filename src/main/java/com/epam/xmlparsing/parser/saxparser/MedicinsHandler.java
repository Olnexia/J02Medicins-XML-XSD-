package com.epam.xmlparsing.parser.saxparser;

import com.epam.xmlparsing.entity.Drug;
import com.epam.xmlparsing.entity.MedicalFulfillment;
import com.epam.xmlparsing.entity.PrescriptionalDrug;
import com.epam.xmlparsing.entity.Vitamin;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class MedicinsHandler extends DefaultHandler {
    private List<Drug> medicins;
    private Drug current = null;
    private MedicinsEnum currentEnum = null;
    private EnumSet<MedicinsEnum> withText;

    public MedicinsHandler(){
        medicins = new ArrayList <>();
        withText = EnumSet.range(MedicinsEnum.ID,MedicinsEnum.DAILY_NEED);
    }

    public List<Drug> getMedicins(){
        return medicins;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch(localName){
            case "drug":
                current = new Drug();
                current.setName(attributes.getValue(0));
                break;
            case "vitamin":
                current = new Vitamin();
                current.setName(attributes.getValue(0));
                break;
            case "prescriptional-drug":
                current = new PrescriptionalDrug();
                current.setName(attributes.getValue(0));
                break;
                default:
                    MedicinsEnum temp = MedicinsEnum.valueOf(localName.toUpperCase().replaceAll("-","_"));
                    if(withText.contains(temp)){
                        currentEnum = temp;
                    }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName){
        if("drug".equals(localName)
                ||"vitamin".equals(localName)
                ||"prescriptional-drug".equals(localName)){
            medicins.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length){
        String content = new String (ch,start,length).trim();
        if(currentEnum == null){
            return;
        }
        switch (currentEnum){
            case ID:
                current.getCertificate().setId(Long.parseLong(content));
                break;
            case PRICE:
                current.setPrice(new BigDecimal(content));
                break;
            case DOSAGE:
                current.setDosage(Double.parseDouble(content));
                break;
            case AMOUNT:
                current.setAmount(Integer.parseInt(content));
                break;
            case FULFILLMENT:
                current.setFulfillment(MedicalFulfillment.valueOf(content.toUpperCase()));
                break;
            case NAME:
                current.setName(content);
                break;
            case PHARM:
                current.setPharm(content);
                break;
            case ISSUE_DATE:
                String [] issueDate = content.split("\\.");
                current.getCertificate().setIssueDate(LocalDate.of(Integer.parseInt(issueDate[2]),Integer.parseInt(issueDate[1]),Integer.parseInt(issueDate[0])));
                break;
            case EXPIRATION_DATE:
                String [] expirationDate = content.split("\\.");
                current.getCertificate().setExpirationDate(LocalDate.of(Integer.parseInt(expirationDate[2]),Integer.parseInt(expirationDate[1]),Integer.parseInt(expirationDate[0])));
                break;
            case REGISTERING_ORGANIZATION:
                current.getCertificate().setRegisteringOrganization(content);
                break;
            case ADDICTIVE:
                ((PrescriptionalDrug) current).setAddictive(Boolean.parseBoolean(content));
                break;
            case NARCOTICAL:
                ((PrescriptionalDrug) current).setNarcotical(Boolean.parseBoolean(content));
                break;
            case DAILY_NEED:
                ((Vitamin)current).setDailyNeed(Integer.parseInt(content));
                break;
                default:
                    System.out.println(content);
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(),currentEnum.name());
        }
        currentEnum = null;
    }
}
