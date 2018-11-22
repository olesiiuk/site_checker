package com.example.site_reader.model.sitechecker;


import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface SiteChecker {
    Map<String, Integer> checkSites(List<String> siteURLs);
}
