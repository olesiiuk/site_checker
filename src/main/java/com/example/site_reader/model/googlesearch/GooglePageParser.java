package com.example.site_reader.model.googlesearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GooglePageParser implements LinkSearcher {
    private Set<String> linkSet;

    private Logger logger = LogManager.getLogger(GooglePageParser.class);

    private GooglePageDownloader googlePageDownloader;

    @Autowired
    public GooglePageParser(GooglePageDownloader googlePageDownloader) {
        this.googlePageDownloader = googlePageDownloader;
    }

    @Override
    public Set<String> getSearchResultLinks(String searchRequest, int resultQuantity) {
        Document doc = googlePageDownloader.getPage(searchRequest, resultQuantity);

        Elements results = doc.select("h3.r > a");
        linkSet = new HashSet<>();

        for (Element result : results) {
            String linkHref = result.attr("href");

            addValidLink(linkHref);
        }

        logger.info("For request " + "\"" + searchRequest + "\" " + "found " + linkSet.size() + " results");

        return linkSet;
    }

    private void addValidLink(String linkHref) {
        String link = getRootOfURL(linkHref);

        if (!link.equals("")) {
            linkSet.add(link);
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
