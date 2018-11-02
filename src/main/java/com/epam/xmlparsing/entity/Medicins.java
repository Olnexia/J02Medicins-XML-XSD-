package com.epam.xmlparsing.entity;

public class Medicins {
    private String name;
    private String pharm;
    private Certificate certificate;
    private MedicalFulfillment fulfillment;
    private PackageType packageType;
    private int amount;
    private double price;
    private int dosage;
    private int periodicity;

    public Medicins(String name, String pharm, Certificate certificate, MedicalFulfillment fulfillment, PackageType packageType, int amount, double price, int dosage, int periodicity) {
        this.name = name;
        this.pharm = pharm;
        this.certificate = certificate;
        this.fulfillment = fulfillment;
        this.packageType = packageType;
        this.amount = amount;
        this.price = price;
        this.dosage = dosage;
        this.periodicity = periodicity;
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

    public PackageType getPackageType() {
        return packageType;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public int getDosage() {
        return dosage;
    }

    public int getPeriodicity() {
        return periodicity;
    }
}
