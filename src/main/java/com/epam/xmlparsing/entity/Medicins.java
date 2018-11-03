package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "medicins")
public class Medicins {
    @XmlElements({
            @XmlElement(name = "drug",type = Drug.class),
            @XmlElement(name = "vitamin",type = Vitamin.class),
            @XmlElement(name = "prescriptional-drug",type = PrescriptionalDrug.class)
    })
    private List<Drug> drugs;

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
