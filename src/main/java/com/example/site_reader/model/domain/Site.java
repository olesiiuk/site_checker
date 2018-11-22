package com.example.site_reader.model.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Site {

    @Id
    @Column
    private String domain;

    @Column
    private String searchRequest;

    @Column
    private int position;

    @Column
    private LocalDate date;

    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "site", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private SiteDescription siteDescription;

    public Site() {
    }

    public Site(String domain, String searchRequest, int position, LocalDate date, SiteDescription siteDescription) {
        this.domain = domain;
        this.searchRequest = searchRequest;
        this.position = position;
        this.date = date;
        this.siteDescription = siteDescription;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSearchRequest() {
        return searchRequest;
    }

    public void setSearchRequest(String searchRequest) {
        this.searchRequest = searchRequest;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public SiteDescription getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(SiteDescription siteDescription) {
        this.siteDescription = siteDescription;
    }
}
