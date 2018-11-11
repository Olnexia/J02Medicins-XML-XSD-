package com.epam.xmlparsing.parser.jaxb.adapter;

import com.epam.xmlparsing.entity.MedicalFulfillment;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class FulfillmentAdapter extends XmlAdapter<String, MedicalFulfillment> {
    @Override
    public MedicalFulfillment unmarshal(String fulfillmentSource){
        String fulfillment = fulfillmentSource.toUpperCase();
        return MedicalFulfillment.valueOf(fulfillment);
    }

    @Override
    public String marshal(MedicalFulfillment fulfillment){
        return fulfillment.getValue();
    }
}
