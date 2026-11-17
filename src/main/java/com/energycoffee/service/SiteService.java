package com.energycoffee.service;

import com.energycoffee.exception.ResourceNotFoundException;
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
                        new ResourceNotFoundException("Site not found with ID: " + id));
    }

    public Site updateSite(Long id, Site updatedSite) {
        Site existingSite = getSiteById(id);

        existingSite.setName(updatedSite.getName());
        existingSite.setCity(updatedSite.getCity());
        existingSite.setAddress(updatedSite.getAddress());

        return siteRepository.save(existingSite);
    }
}