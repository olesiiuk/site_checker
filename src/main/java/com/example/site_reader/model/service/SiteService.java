package com.example.site_reader.model.service;

import com.example.site_reader.model.domain.Site;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface SiteService {

    List<Site> findAll();

    void save(Site site);

    List<Site> generateAndSaveSites(Map<String, Integer> checkedSites, String searchRequest);

    Site findById(String domain);

    Optional<Site> findOneByDomain(String domain);

    void deleteSite(Site site);
}
