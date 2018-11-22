package com.example.site_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

public class GoogleSearchTest {
    private final String GOOGLE_SEARCH_URL = "https://www.google.com/search?q=";

    @Test
    public void testGoogleSearchPage() {

        String searchUrl = GOOGLE_SEARCH_URL + "shop&num=15";

        Document doc = null;

        try {
            doc = Jsoup.connect(searchUrl).userAgent("Mozilla/5.0").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements results = doc.select("h3.r > a");

        for (Element result : results) {

            String linkHref = result.attr("href");

            String link = "";
            if (linkHref.startsWith("/url?q=")) {
                link = linkHref.substring(7, linkHref.lastIndexOf("&"));
                System.out.println(link);
            }
        }
    }

}
