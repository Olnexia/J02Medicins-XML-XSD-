package com.epam.xmlparsing.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "certificate")
public class Certificate {
    @XmlElement(name = "id")
    private long id;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "issue-date")
    private  LocalDate issueDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @XmlElement(name = "expiration-date")
    private LocalDate expirationDate;
    @XmlElement(name = "registering-organization")
    private String registeringOrganization;

    public Certificate(){

    }

    public Certificate(long id, LocalDate issueDate, LocalDate expirationDate, String registeringOrganization) {
        this.id = id;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
    }

    public long getId() {
        return id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
    }
}
