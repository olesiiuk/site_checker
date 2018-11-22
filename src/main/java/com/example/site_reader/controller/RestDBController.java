package com.example.site_reader.controller;

import com.example.site_reader.model.domain.SiteDescription;
import com.example.site_reader.model.excepton.SiteNotFoundException;
import com.example.site_reader.model.domain.Site;
import com.example.site_reader.model.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class RestDBController {

    private SiteService siteService;

    @Autowired
    public RestDBController(SiteService siteService) {
        this.siteService = siteService;
    }

    @RequestMapping(value = "/sites", method = RequestMethod.GET)
    public List<Site> getAllSites() {

        List<Site> siteList = siteService.findAll();

        return siteList;
    }

    @RequestMapping(value = "/sites", method = RequestMethod.POST)
    public Site add(@RequestBody Site site) {

        siteService.save(site);

        return site;
    }

    @RequestMapping(value = "/sites/{domain}", method = RequestMethod.GET)
    public Site getSiteById(@PathVariable String domain) {

        return siteService.findOneByDomain(domain).orElseThrow(
                () -> new SiteNotFoundException(domain)
        );
    }

    @RequestMapping(value = "/sites/{domain}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String domain) {
        Site site = siteService.findOneByDomain(domain).orElseThrow(
                () -> new SiteNotFoundException(domain)
        );
        siteService.deleteSite(site);
    }

    @RequestMapping(value = "/sites/{domain}/description", method = RequestMethod.GET)
    public SiteDescription getSiteDescription(@PathVariable String domain) {
        Site site = siteService.findOneByDomain(domain).orElseThrow(() -> new SiteNotFoundException(domain));
        SiteDescription description = site.getSiteDescription();

        return description;
    }

    @RequestMapping(value = "/sites/{domain}/description", method = RequestMethod.PUT)
    public void updateDescription(@PathVariable String domain, @RequestBody SiteDescription siteDescription) {
        Site site = siteService.findOneByDomain(domain).orElseThrow(() -> new SiteNotFoundException(domain));
        site.setSiteDescription(siteDescription);
        siteService.save(site);
    }
}
