package com.energycoffee.repository;

import com.energycoffee.model.Site;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SiteRepositoryTest {

    @Autowired
    private SiteRepository siteRepository;

    @Test
    void shouldSaveSite() {
        Site site = new Site();
        site.setName("Test Office");
        site.setCity("Budapest");
        site.setAddress("Main Street 10");

        Site savedSite = siteRepository.save(site);

        assertThat(savedSite.getId()).isNotNull();
        assertThat(savedSite.getName()).isEqualTo("Test Office");
    }

    @Test
    void shouldFindSiteById() {
        Site site = new Site();
        site.setName("Test Office");
        site.setCity("Budapest");

        Site savedSite = siteRepository.save(site);

        Optional<Site> foundSite = siteRepository.findById(savedSite.getId());

        assertThat(foundSite).isPresent();
        assertThat(foundSite.get().getCity()).isEqualTo("Budapest");
    }

    @Test
    void shouldFindAllSites() {
        Site firstSite = new Site();
        firstSite.setName("Office A");
        firstSite.setCity("Budapest");

        Site secondSite = new Site();
        secondSite.setName("Office B");
        secondSite.setCity("Vienna");

        siteRepository.save(firstSite);
        siteRepository.save(secondSite);

        assertThat(siteRepository.findAll()).hasSize(2);
    }

    @Test
    void shouldDeleteSite() {
        Site site = new Site();
        site.setName("Test Office");
        site.setCity("Budapest");

        Site savedSite = siteRepository.save(site);

        siteRepository.deleteById(savedSite.getId());

        assertThat(siteRepository.findById(savedSite.getId())).isEmpty();
    }
}