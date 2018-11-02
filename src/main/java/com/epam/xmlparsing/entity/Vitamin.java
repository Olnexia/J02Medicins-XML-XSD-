package com.epam.xmlparsing.entity;

public class Vitamin extends TherapeuticDrug {
    private int dailyNeed;

    public Vitamin(String name, String pharm, Certificate certificate, MedicalFulfillment fulfillment, PackageType packageType, int amount, double price, int dosage, int periodicity, int dailyNeed) {
        super(name, pharm, certificate, fulfillment, packageType, amount, price, dosage, periodicity);
        this.dailyNeed = dailyNeed;
    }

    public int getDailyNeed() {
        return dailyNeed;
    }
}
