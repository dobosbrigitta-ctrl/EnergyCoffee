
package com.energycoffee.controller;

import com.energycoffee.model.Site;
import com.energycoffee.service.SiteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping
    public Site createSite(@RequestBody Site site) {
        return siteService.saveSite(site);
    }

    @GetMapping
    public List<Site> getAllSites() {
        return siteService.getAllSites();
    }

    @GetMapping("/{id}")
    public Site getSiteById(@PathVariable Long id) {
        return siteService.getSiteById(id);
    }
}
