package com.epam.xmlparsing.parser.sax;

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
    private static final String DRUG = "drug";
    private static final String VITAMIN = "vitamin";
    private static final String PRESCRIPTIONAL_DRUG = "prescriptional-drug";
    private static final int YEAR_INDEX = 2;
    private static final int MONTH_INDEX = 1;
    private static final int DAY_INDEX = 0;
    private List <Drug> medicins = new ArrayList <>();
    private Drug current = null;
    private MedicinsElements currentEnum = null;
    private EnumSet <MedicinsElements> withText;

    public MedicinsHandler() {
        withText = EnumSet.range(MedicinsElements.ID, MedicinsElements.DAILY_NEED);
    }

    public List <Drug> getMedicins() {
        return medicins;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (localName) {
            case DRUG:
                current = new Drug();
                current.setName(attributes.getValue(0));
                break;
            case VITAMIN:
                current = new Vitamin();
                current.setName(attributes.getValue(0));
                break;
            case PRESCRIPTIONAL_DRUG:
                current = new PrescriptionalDrug();
                current.setName(attributes.getValue(0));
                break;
            default:
                String enumLocalName = localName.toUpperCase().replaceAll("-", "_");
                MedicinsElements temp = MedicinsElements.valueOf(enumLocalName);
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (DRUG.equals(localName)
                || VITAMIN.equals(localName)
                || PRESCRIPTIONAL_DRUG.equals(localName)) {
            medicins.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String content = new String(ch, start, length).trim();
        if (currentEnum == null) {
            return;
        }
        switch (currentEnum) {
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
                String[] issueDate = content.split("\\.");
                current.getCertificate().setIssueDate(LocalDate.of(Integer.parseInt(issueDate[YEAR_INDEX]),
                                                                    Integer.parseInt(issueDate[MONTH_INDEX]),
                                                                    Integer.parseInt(issueDate[DAY_INDEX])));
                break;
            case EXPIRATION_DATE:
                String[] expirationDate = content.split("\\.");
                current.getCertificate().setExpirationDate(LocalDate.of(Integer.parseInt(expirationDate[YEAR_INDEX]),
                                                            Integer.parseInt(expirationDate[MONTH_INDEX]),
                                                            Integer.parseInt(expirationDate[DAY_INDEX])));
                break;
            case REGISTERING_ORGANIZATION:
                current.getCertificate().setRegisteringOrganization(content);
                break;
            case ADDICTIVE:
                ((PrescriptionalDrug) current).setAddictive(Boolean.parseBoolean(content));
                break;
            case NARCOTIC:
                ((PrescriptionalDrug) current).setNarcotic(Boolean.parseBoolean(content));
                break;
            case DAILY_NEED:
                ((Vitamin) current).setDailyNeed(Integer.parseInt(content));
                break;
            default:
                throw new EnumConstantNotPresentException(
                        currentEnum.getDeclaringClass(), currentEnum.name());
        }
        currentEnum = null;
    }
}
