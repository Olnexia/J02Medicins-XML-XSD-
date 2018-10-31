package com.epam.xmlparsing.entity;

import java.util.Date;

public class Certificate {
    private final long id;
    private final Date issueDate;
    private final Date expirationDate;
    private final String registeringOrganization;

    public Certificate(long id, Date issueDate, Date expirationDate, String registeringOrganization) {
        this.id = id;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
    }

    public long getId() {
        return id;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }
}
