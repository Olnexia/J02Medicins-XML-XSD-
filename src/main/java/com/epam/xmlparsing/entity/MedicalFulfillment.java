package com.epam.xmlparsing.entity;

public enum MedicalFulfillment {
    PILLS("PILLS"),
    CAPSULES("CAPSULE"),
    POWDER("POWDER"),
    DROPS("DROPS");

    private String value;
    MedicalFulfillment(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
