package com.example.site_reader.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class SiteDescription {

    @Id
    @Column
    private String domain;

    @Column
    private String email;

    @Column
    private String label;

    @Column
    private String PSVersion;

    @Column
    private String template;

    @Column
    private String adaptive;

    @Column
    private String googleSpeedDesktop;

    @Column
    private String GSTMobile;

    @Column
    private String downloadTime;

    @Column
    private int errors;

    @Column
    private String seo;

    @Column
    private String friendlyLinks;

    @Column
    private String structure;

    @Column
    private String extraHeadings;

    @Column
    private String links;

    @Column
    private String texts;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "domain")
    private Site site;

    public SiteDescription() {
    }

    public SiteDescription(String domain) {
        this.domain = domain;
    }

    public Site getSite() {
        return site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String siteId) {
        this.domain = siteId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPSVersion() {
        return PSVersion;
    }

    public void setPSVersion(String PSVersion) {
        this.PSVersion = PSVersion;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getAdaptive() {
        return adaptive;
    }

    public void setAdaptive(String adaptive) {
        this.adaptive = adaptive;
    }

    public String getGoogleSpeedDesktop() {
        return googleSpeedDesktop;
    }

    public void setGoogleSpeedDesktop(String googleSpeedDesktop) {
        this.googleSpeedDesktop = googleSpeedDesktop;
    }

    public String getGSTMobile() {
        return GSTMobile;
    }

    public void setGSTMobile(String GSTMobile) {
        this.GSTMobile = GSTMobile;
    }

    public String getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(String downloadTime) {
        this.downloadTime = downloadTime;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public String getFriendlyLinks() {
        return friendlyLinks;
    }

    public void setFriendlyLinks(String friendlyLinks) {
        this.friendlyLinks = friendlyLinks;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public String getExtraHeadings() {
        return extraHeadings;
    }

    public void setExtraHeadings(String extraHeadings) {
        this.extraHeadings = extraHeadings;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public String getTexts() {
        return texts;
    }

    public void setTexts(String texts) {
        this.texts = texts;
    }
}
