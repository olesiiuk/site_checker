package com.example.site_reader.controller;

import com.example.site_reader.model.domain.Site;
import com.example.site_reader.model.googlesearch.LinkSearcher;
import com.example.site_reader.model.service.SiteService;
import com.example.site_reader.model.sitechecker.SiteChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RestSearcherController {

    private LinkSearcher linkSearcher;

    private SiteService siteService;

    private SiteChecker siteChecker;

    @Autowired
    public RestSearcherController(LinkSearcher linkSearcher, SiteService siteService, SiteChecker siteChecker) {
        this.linkSearcher = linkSearcher;
        this.siteService = siteService;
        this.siteChecker = siteChecker;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Site> searchSites(@RequestParam(required = false, defaultValue = "") String searchRequest,
                                  @RequestParam(required = false, defaultValue = "0") int resultQuantity) {

            List<String> sitesToCheck = linkSearcher.getSearchResultLinks(searchRequest, resultQuantity);
        Map<String, Integer> checkedSites = siteChecker.checkSites(sitesToCheck);

        List<Site> siteList = siteService.generateAndSaveSites(checkedSites, searchRequest);

        return siteList;
    }


}
