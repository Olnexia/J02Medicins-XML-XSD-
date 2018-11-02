package com.epam.xmlparsing.entity;

public class PrescriptionalTherapeuticDrug extends TherapeuticDrug {
    private String activeSubstance;
    private boolean addictive;
    private int stayInBodyDuration;
    private int prescriptionValidity;

    public PrescriptionalTherapeuticDrug(String name, String pharm, Certificate certificate, MedicalFulfillment fulfillment, PackageType packageType, int amount, double price, int dosage, int periodicity, String activeSubstance, boolean addictive, int stayInBodyDuration, int prescriptionValidity) {
        super(name, pharm, certificate, fulfillment, packageType, amount, price, dosage, periodicity);
        this.activeSubstance = activeSubstance;
        this.addictive = addictive;
        this.stayInBodyDuration = stayInBodyDuration;
        this.prescriptionValidity = prescriptionValidity;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public boolean isAddictive() {
        return addictive;
    }

    public int getStayInBodyDuration() {
        return stayInBodyDuration;
    }

    public int getPrescriptionValidity() {
        return prescriptionValidity;
    }
}
