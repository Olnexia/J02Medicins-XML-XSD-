package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
    @XmlJavaTypeAdapter(value = FulfillmentAdapter.class)
    @XmlElement(name ="fulfillment")
    private MedicalFulfillment fulfillment;

    public Drug(){
        this.certificate = new Certificate();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void setFulfillment(MedicalFulfillment fulfillment) {
        this.fulfillment = fulfillment;
    }
}
