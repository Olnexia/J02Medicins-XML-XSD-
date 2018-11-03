package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "drug")
public class Drug {
    @XmlAttribute(name ="name")
    private String name;
    @XmlElement(name ="pharm")
    private String pharm;
    @XmlElement(name ="amount")
    private int amount;
    @XmlElement(name ="price")
    private BigDecimal price;
    @XmlElement(name ="dosage")
    private double dosage;
    @XmlElement(name ="certificate")
    private Certificate certificate;
    @XmlElement(name ="fulfillment")
    private MedicalFulfillment fulfillment;

    public Drug(){

    }

    public Drug(String name, String pharm, Certificate certificate, MedicalFulfillment fulfillment, int amount, BigDecimal price, int dosage) {
        this.name = name;
        this.pharm = pharm;
        this.certificate = certificate;
        this.fulfillment = fulfillment;
        this.amount = amount;
        this.price = price;
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public String getPharm() {
        return pharm;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public MedicalFulfillment getFulfillment() {
        return fulfillment;
    }

    public int getAmount() {
        return amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public double getDosage() {
        return dosage;
    }
}
