package com.example.site_reader.controller;

import com.example.site_reader.model.domain.Site;
import com.example.site_reader.model.fileworker.FileModel;
import com.example.site_reader.model.fileworker.FileParser;
import com.example.site_reader.model.service.SiteService;
import com.example.site_reader.model.sitechecker.SiteChecker;
import com.example.site_reader.model.googlesearch.LinkSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class SiteCheckerController {

    @Autowired
    private SiteService siteService;

    private LinkSearcher linkSearcher;

    private SiteChecker siteChecker;

    private FileParser fileParser;

    @Autowired
    public SiteCheckerController(SiteChecker siteChecker, FileParser fileParser, LinkSearcher linkSearcher) {
        this.linkSearcher = linkSearcher;
        this.siteChecker = siteChecker;
        this.fileParser = fileParser;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homepage() {
        ModelAndView modelAndView = new ModelAndView("index");

        FileModel fileModel = new FileModel();
        modelAndView.addObject("fileModel", fileModel);

        return modelAndView;
    }

    @RequestMapping(value = "/base", method = RequestMethod.GET)
    public ModelAndView sitesPage() {
        ModelAndView modelAndView = new ModelAndView("base");

        List<Site> siteList = siteService.findAll();
        modelAndView.addObject("siteList", siteList);

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView uploadFile(@Validated FileModel file, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("home");

        List<String> sitesToCheck = fileParser.getURLsFromFile(file.getFile());
        Map<String, Integer> checkedSites = siteChecker.checkSites(sitesToCheck);

        modelAndView.addObject("checkedSites", checkedSites);
        modelAndView.addObject("successMessage", "File has been uploaded");

        return modelAndView;
    }

    @RequestMapping(value = "/q", method = RequestMethod.GET)
    public ModelAndView getGoogleParams(@RequestParam(required = false, defaultValue = "") String searchRequest,
                                        @RequestParam(required = false, defaultValue = "0") int resultQuantity) {

        ModelAndView modelAndView = new ModelAndView("home");

        List<String> sitesToCheck = linkSearcher.getSearchResultLinks(searchRequest, resultQuantity);
        Map<String, Integer> checkedSites = siteChecker.checkSites(sitesToCheck);
        List<Site> siteList = siteService.generateAndSaveSites(checkedSites, searchRequest);

        modelAndView.addObject("query", "Your search request: " + "\"" + searchRequest + "\""
                + " quantity of searched results: " + resultQuantity);
        modelAndView.addObject("checkedSites", checkedSites);

        return modelAndView;
    }

}
