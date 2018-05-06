package com.example.site_reader.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class PrestaShopCheckerWithJSOUP implements SiteChecker {

    private final String ELEMENT_NAME = "generator";
    private final String ELEMENT_CONTENT = "prestashop";

    public Map<String, String> checkSites(Set<String> siteURLs) {
        Map<String, String> checkedSites = new HashMap<>();

        for (String url : siteURLs) {
            String result = checkSite(url);

            checkedSites.put(url, result);
        }
        return checkedSites;
    }

    public String checkSite(String url) {
        Document doc = getDocument(url);
        if (doc == null) {
            return "Couldn't get html file";
        }

        if (isPrestaShopSite(doc)) {
            return "Presta shop site";
        }

        return "Not a presta shop site";
    }

    private Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private boolean isPrestaShopSite(Document doc) {
        Elements metaElements = doc.getElementsByTag("meta");

        for (Element element : metaElements) {
            String name = element.attr("name");
            String content = element.attr("content");

            if (name.equalsIgnoreCase(ELEMENT_NAME) && content.equalsIgnoreCase(ELEMENT_CONTENT)) {
                return true;
            }
        }

        return false;
    }

}
