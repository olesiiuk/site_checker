package com.example.site_reader.model.service;

import com.example.site_reader.model.Repository.SiteRepository;
import com.example.site_reader.model.domain.Site;
import com.example.site_reader.model.domain.SiteDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SiteServiceImpl implements SiteService {

    private SiteRepository siteRepository;

    @Autowired
    public SiteServiceImpl(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    @Override
    public List<Site> findAll() {
        return siteRepository.findAll();
    }

    @Override
    public void save(Site site) {
        siteRepository.save(site);
    }


    //TODO refactor this method ! ! ! ! ! !
    @Override
    public List<Site> generateAndSaveSites(Map<String, Integer> checkedSites, String searchRequest) {

        List<Site> siteList = new ArrayList<>();

        checkedSites.forEach((url, index) -> {
            Site site;
            if (!siteRepository.findOneByDomain(url).isPresent()) {

                site = new Site(url, searchRequest, index, LocalDate.now(), new SiteDescription(url));
                siteRepository.save(site);
            }
            site = siteRepository.findOneByDomain(url).get();

            siteList.add(site);
        });

        return siteList;
    }

    @Override
    public Site findById(String domain) {
        return siteRepository.getOne(domain);
    }

    public Optional<Site> findOneByDomain(String domain) {

        return siteRepository.findById(domain);

    }

    public void deleteSite(Site site) {
        siteRepository.delete(site);
    }

}
