package com.example.site_reader.model.googlesearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GooglePageParser implements LinkSearcher {
    private List<String> links;

    private Logger logger = LogManager.getLogger(GooglePageParser.class);

    private GooglePageDownloader googlePageDownloader;

    @Autowired
    public GooglePageParser(GooglePageDownloader googlePageDownloader) {
        this.googlePageDownloader = googlePageDownloader;
    }

    public List<String> getSearchResultLinks(String searchRequest, int resultQuantity) {
        if (isMultipartRequest(searchRequest)) {
            return getMultipleSearchResultLinks(searchRequest, resultQuantity);
        } else {
            return getOneSearchResultLinks(searchRequest, resultQuantity);
        }
    }

    private boolean isMultipartRequest(String searchRequest) {
        return searchRequest.contains(",");
    }

    public List<String> getMultipleSearchResultLinks(String searchRequest, int resultQuantity) {
        String[] requests = searchRequest.split(",");

        List<String> result = new ArrayList<>();

        for (String request : requests) {
            result.addAll(getOneSearchResultLinks(request, resultQuantity));
        }

        return result;
    }

    public List<String> getOneSearchResultLinks(String searchRequest, int resultQuantity) {
        Document doc = googlePageDownloader.getPage(searchRequest, resultQuantity);

        Elements results = doc.select("h3.r > a");
        links = new ArrayList<>();

        for (Element result : results) {
            String linkHref = result.attr("href");

            addValidLink(linkHref);
        }

        logger.info("For request " + "\"" + searchRequest + "\" " + "found " + links.size() + " results");

        return links;
    }

    private void addValidLink(String linkHref) {
        String link = getRootOfURL(linkHref);

        if (!link.equals("")) {
            links.add(link);
        }
    }

    private String getRootOfURL(String linkHref) {
        String link = "";
        if (linkHref.startsWith("/url?q=")) {
            link = cutPrefix(linkHref);
        }
        link = cutSuffix(link);
        return link;
    }

    private String cutPrefix(String linkHref) {
        return linkHref.substring(7, linkHref.length());
    }

    protected String cutSuffix(String link) {
        if (link.contains("&")) {
            link = link.substring(0, link.indexOf("&"));
        }

        String temp = "";
        if (link.length() > 8) {
            temp = link.substring(8, link.length());
        }

        if (temp.contains("/")) {

            int slashIndex = 8 + temp.indexOf("/");
            link = link.substring(0, slashIndex);
        }

        return link;
    }
}
