package com.epam.xmlparsing.entity;

public enum MedicalFulfillment {
    PILLS("pills"),
    CAPSULES("capsules"),
    POWDER("powder"),
    DROPS("drops");

    private String value;
    MedicalFulfillment(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
