package com.example.site_reader.model.Repository;

import com.example.site_reader.model.domain.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, String> {

    Optional<Site> findOneByDomain(String domain);

}
