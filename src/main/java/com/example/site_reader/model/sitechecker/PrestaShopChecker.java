package com.example.site_reader.model.sitechecker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PrestaShopChecker implements SiteChecker {

    private static final Logger logger = LogManager.getLogger(PrestaShopChecker.class);

    private final String ELEMENT_NAME = "generator";
    private final String ELEMENT_CONTENT = "prestashop";

    public Map<String, Integer> checkSites(List<String> siteURLs) {

        Map<String, Integer> checkedSites = new HashMap<>();

        for (int index = 0; index < siteURLs.size(); index++) {

            String url = siteURLs.get(index);
            if (isPrestaShopSite(url)) {
                String domain = getDomain(url);
                checkedSites.put(domain, index);
            }

        }
        return checkedSites;
    }

    protected String getDomain(String url) {

        if (url.contains("//")) {
            return url.substring(url.indexOf("//") + 2);
        }
        return url;
    }

    public boolean isPrestaShopSite(String url) {
        Document doc = getDocument(url);
        if (doc == null) {
            logger.info("site " + url + " cant be reached");
            return false;
        }
        return containsPrestaTag(doc);
    }

    private Document getDocument(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            logger.info("Checking site: " + url);
        } catch (EOFException e) {
            System.out.println("EOFException happened");
        } catch (IOException e) {
            System.out.println("Could not get a file from URL: " + url);
        }
        return doc;
    }

    private boolean containsPrestaTag(Document doc) {
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
