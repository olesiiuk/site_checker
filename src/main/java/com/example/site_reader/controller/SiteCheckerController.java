package com.example.site_reader.controller;

import com.example.site_reader.model.FileModel;
import com.example.site_reader.model.FileParser;
import com.example.site_reader.model.SiteChecker;
import com.example.site_reader.model.googlesearch.LinkSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

@Controller
public class SiteCheckerController {

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
        ModelAndView modelAndView = new ModelAndView("home");

        FileModel fileModel = new FileModel();
        modelAndView.addObject("fileModel", fileModel);

        return modelAndView;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ModelAndView uploadFile(@Validated FileModel file, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("home");

        System.out.println("\n ========================================== \n");
        System.out.println("file has been uploaded");
        System.out.println(file.getFile().getSize());
        System.out.println("\n ========================================== \n");

        Set<String> sitesToCheck = fileParser.getURLsFromFile(file.getFile());
        Map<String, String> checkedSites = siteChecker.checkSites(sitesToCheck);

        modelAndView.addObject("checkedSites", checkedSites);
        modelAndView.addObject("successMessage", "File has been uploaded");

        return modelAndView;
    }

    @RequestMapping(value = "/q", method = RequestMethod.GET)
    public ModelAndView getGoogleParams(@RequestParam(required = false, defaultValue = "") String searchRequest,
                                        @RequestParam(required = false, defaultValue = "0") int resultQuantity) {

        ModelAndView modelAndView = new ModelAndView("home");

        Set<String> sitesToCheck = linkSearcher.getSearchResultLinks(searchRequest, resultQuantity);
        Map<String, String> checkedSites = siteChecker.checkSites(sitesToCheck);

        modelAndView.addObject("query", "Your search request: " + "\"" + searchRequest + "\""
                + " quantity of searched results: " + resultQuantity);
        modelAndView.addObject("checkedSites", checkedSites);

        return modelAndView;
    }


}
