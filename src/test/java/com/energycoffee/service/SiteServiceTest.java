package com.energycoffee.service;

import com.energycoffee.exception.ResourceNotFoundException;
import com.energycoffee.model.Site;
import com.energycoffee.repository.SiteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.energycoffee.exception.DuplicateResourceException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SiteServiceTest {

    @Mock
    private SiteRepository siteRepository;

    @InjectMocks
    private SiteService siteService;

    @Test
    void shouldSaveSite() {
        Site site = new Site();
        site.setName("Test Office");
        site.setCity("Budapest");

        when(siteRepository.save(site)).thenReturn(site);

        Site savedSite = siteService.saveSite(site);

        assertThat(savedSite).isSameAs(site);
        verify(siteRepository).save(site);
    }

    @Test
    void shouldReturnAllSites() {
        Site firstSite = new Site();
        firstSite.setName("Office A");
        firstSite.setCity("Budapest");

        Site secondSite = new Site();
        secondSite.setName("Office B");
        secondSite.setCity("Vienna");

        when(siteRepository.findAll()).thenReturn(List.of(firstSite, secondSite));

        List<Site> sites = siteService.getAllSites();

        assertThat(sites).hasSize(2);
        assertThat(sites).containsExactly(firstSite, secondSite);
        verify(siteRepository).findAll();
    }

    @Test
    void shouldReturnSiteById() {
        Site site = new Site();
        site.setName("Test Office");
        site.setCity("Budapest");

        when(siteRepository.findById(1L)).thenReturn(Optional.of(site));

        Site foundSite = siteService.getSiteById(1L);

        assertThat(foundSite).isSameAs(site);
        verify(siteRepository).findById(1L);
    }

    @Test
    void shouldUpdateSite() {
        Site existingSite = new Site();
        existingSite.setName("Old Office");
        existingSite.setCity("Budapest");
        existingSite.setAddress("Old Address");

        Site updatedSite = new Site();
        updatedSite.setName("New Office");
        updatedSite.setCity("Vienna");
        updatedSite.setAddress("New Address");

        when(siteRepository.findById(1L)).thenReturn(Optional.of(existingSite));
        when(siteRepository.save(existingSite)).thenReturn(existingSite);

        Site result = siteService.updateSite(1L, updatedSite);

        assertThat(result.getName()).isEqualTo("New Office");
        assertThat(result.getCity()).isEqualTo("Vienna");
        assertThat(result.getAddress()).isEqualTo("New Address");

        verify(siteRepository).findById(1L);
        verify(siteRepository).save(existingSite);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistingSite() {
        Site updatedSite = new Site();
        updatedSite.setName("New Office");
        updatedSite.setCity("Vienna");
        updatedSite.setAddress("New Address");

        when(siteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> siteService.updateSite(99L, updatedSite))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Site not found with ID: 99");

        verify(siteRepository).findById(99L);
        verify(siteRepository, never()).save(any(Site.class));
    }

    @Test
    void shouldThrowExceptionWhenSiteDoesNotExist() {
        when(siteRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> siteService.getSiteById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Site not found with ID: 99");

        verify(siteRepository).findById(99L);
    }
    @Test
    void shouldThrowExceptionWhenExternalIdAlreadyExists() {
        Site site = new Site();
        site.setExternalId("SITE-001");
        site.setName("Test Office");
        site.setCity("Budapest");

        when(siteRepository.existsByExternalId("SITE-001")).thenReturn(true);

        assertThatThrownBy(() -> siteService.saveSite(site))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessage("Site already exists with external ID: SITE-001");

        verify(siteRepository).existsByExternalId("SITE-001");
        verify(siteRepository, never()).save(any(Site.class));
    }
}