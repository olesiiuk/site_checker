package com.example.site_reader.model;


import java.util.Map;
import java.util.Set;


public interface SiteChecker {
    Map<String, String> checkSites(Set<String> siteURLs);
}
