package com.example.site_reader.controller;

import com.example.site_reader.model.FileModel;
import com.example.site_reader.model.FileParser;
import com.example.site_reader.model.SiteChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

@Controller
public class SiteCheckerController {

    private SiteChecker siteChecker;

    private FileParser fileParser;

    @Autowired
    public SiteCheckerController(SiteChecker siteChecker, FileParser fileParser) {
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

        //TODO handle file and put data into model

        Set<String> sitesToCheck = fileParser.getURLsFromFile(file.getFile());
        Map<String, String> checkedSites = siteChecker.checkSites(sitesToCheck);

        modelAndView.addObject("checkedSites", checkedSites);
        modelAndView.addObject("successMessage", "File has been uploaded");

        return modelAndView;
    }


}
