package com.example.site_reader.model.googlesearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GooglePageDownloader {

    Logger logger = LogManager.getLogger(GooglePageDownloader.class);

    private final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=";
    private final String NUMBER_OF_REQUESTS_PARAM = "&num=";

    public Document getPage(String searchRequest, int quantityOfResults) {

        String searchURL = compileSearchURL(searchRequest, quantityOfResults);

                Document doc = null;

        try {
            doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();
        } catch (IOException e) {
            logger.info("Could not get a google page. IOException");
            e.printStackTrace();
        }

        return doc;
    }

    private String compileSearchURL(String searchRequest, int quantityOfResults) {
        return GOOGLE_SEARCH_URL + searchRequest + NUMBER_OF_REQUESTS_PARAM + quantityOfResults;
    }
}
