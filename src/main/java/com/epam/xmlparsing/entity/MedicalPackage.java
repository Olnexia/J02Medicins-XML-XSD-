package com.epam.xmlparsing.entity;

public class MedicalPackage {
    private final PackageType packageType;
    private final int pillAmount;
    private final double price;

    public MedicalPackage(PackageType packageType, int pillAmount, double price) {
        this.packageType = packageType;
        this.pillAmount = pillAmount;
        this.price = price;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    public int getPillAmount() {
        return pillAmount;
    }

    public double getPrice() {
        return price;
    }
}
