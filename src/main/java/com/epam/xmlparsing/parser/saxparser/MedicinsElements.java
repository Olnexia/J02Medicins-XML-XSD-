package com.epam.xmlparsing.parser.saxparser;

public enum MedicinsElements {
    MEDICINS("medicins"),
    DRUG("drug"),
    VITAMIN("vitamin"),
    PRESCRIPTIONAL_DRUG("prescriptional-drug"),
    CERTIFICATE("certificate"),
    ID("id"),
    FULFILLMENT("fulfillment"),
    PRICE("price"),
    DOSAGE("dosage"),
    AMOUNT("amount"),
    NAME("name"),
    PHARM("pharm"),
    ISSUE_DATE("issue-date"),
    EXPIRATION_DATE("expiration-date"),
    REGISTERING_ORGANIZATION("registering-organization"),
    ADDICTIVE("addictive"),
    NARCOTIC("narcotic"),
    DAILY_NEED("daily-need");

    private String value;
    MedicinsElements(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
