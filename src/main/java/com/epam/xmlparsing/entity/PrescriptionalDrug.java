package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlSeeAlso({Drug.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "prescriptional-drug")
public class PrescriptionalDrug extends Drug {
    @XmlElement(name ="addictive")
    private boolean addictive;
    @XmlElement(name ="narcotical")
    private boolean narcotical;

    public PrescriptionalDrug(){

    }

    public PrescriptionalDrug(String name, String pharm, Certificate certificate, MedicalFulfillment fulfillment,
                              int amount, BigDecimal price, int dosage,
                              boolean addictive, boolean narcotical) {
        super(name, pharm, certificate, fulfillment, amount, price, dosage);
        this.addictive = addictive;
        this.narcotical = narcotical;
    }

    public boolean isAddictive() {
        return addictive;
    }

    public boolean isNarcotical() {
        return narcotical;
    }

    public void setAddictive(boolean addictive) {
        this.addictive = addictive;
    }

    public void setNarcotical(boolean narcotical) {
        this.narcotical = narcotical;
    }
}

