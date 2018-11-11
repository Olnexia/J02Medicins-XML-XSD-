package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlSeeAlso({Drug.class})
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vitamin")
public class Vitamin extends Drug {
    @XmlElement(name ="daily-need")
    private int dailyNeed;

    public Vitamin(){
    }

    public Vitamin(String name, String pharm, Certificate certificate, MedicalFulfillment fulfillment,
                   int amount, BigDecimal price, int dosage, int dailyNeed) {
        super(name, pharm, certificate, fulfillment, amount, price, dosage);
        this.dailyNeed = dailyNeed;
    }

    public int getDailyNeed() {
        return dailyNeed;
    }

    public void setDailyNeed(int dailyNeed) {
        this.dailyNeed = dailyNeed;
    }
}
