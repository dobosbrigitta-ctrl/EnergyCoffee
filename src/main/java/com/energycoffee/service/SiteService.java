
package com.energycoffee.service;

import com.energycoffee.model.Site;
import com.energycoffee.repository.SiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {

    private final SiteRepository siteRepository;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Site saveSite(Site site) {
        return siteRepository.save(site);
    }

    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Site getSiteById(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Site not found with ID: " + id));
    }
}
