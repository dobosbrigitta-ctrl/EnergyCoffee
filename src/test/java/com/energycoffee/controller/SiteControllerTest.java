package com.energycoffee.controller;

import com.energycoffee.exception.GlobalExceptionHandler;
import com.energycoffee.exception.ResourceNotFoundException;
import com.energycoffee.model.Site;
import com.energycoffee.service.SiteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.ArgumentMatchers.eq;

@WebMvcTest(SiteController.class)
@Import(GlobalExceptionHandler.class)
class SiteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SiteService siteService;

    @Test
    void createSiteShouldReturnCreatedSite() throws Exception {
        Site site = new Site();
        site.setExternalId("SITE-001");
        site.setName("Office");
        site.setCity("Budapest");
        site.setAddress("Main Street 10");

        when(siteService.saveSite(any(Site.class))).thenReturn(site);

        mockMvc.perform(post("/api/sites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "externalId": "SITE-001",
                                    "name": "Office",
                                    "city": "Budapest",
                                    "address": "Main Street 10"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Office"))
                .andExpect(jsonPath("$.city").value("Budapest"));
    }

    @Test
    void createSiteShouldReturnBadRequestWhenNameIsBlank() throws Exception {
        mockMvc.perform(post("/api/sites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "externalId": "SITE-001",
                                    "name": "",
                                    "city": "Budapest",
                                    "address": "Main Street 10"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getSiteByIdShouldReturnNotFoundWhenSiteDoesNotExist() throws Exception {
        when(siteService.getSiteById(999L))
                .thenThrow(new ResourceNotFoundException("Site not found with ID: 999"));

        mockMvc.perform(get("/api/sites/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Site not found with ID: 999"));
    }
    @Test
    void updateSiteShouldReturnUpdatedSite() throws Exception {
        Site updatedSite = new Site();
        updatedSite.setExternalId("SITE-001");
        updatedSite.setName("Updated Office");
        updatedSite.setCity("Vienna");
        updatedSite.setAddress("New Street 20");

        when(siteService.updateSite(eq(1L), any(Site.class)))
                .thenReturn(updatedSite);

        mockMvc.perform(put("/api/sites/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "externalId": "SITE-001",
                                "name": "Updated Office",
                                "city": "Vienna",
                                "address": "New Street 20"
                            }
                            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Office"))
                .andExpect(jsonPath("$.city").value("Vienna"))
                .andExpect(jsonPath("$.address").value("New Street 20"));
    }
    @Test
    void updateSiteShouldReturnNotFoundWhenSiteDoesNotExist() throws Exception {
        when(siteService.updateSite(eq(999L), any(Site.class)))
                .thenThrow(new ResourceNotFoundException("Site not found with ID: 999"));

        mockMvc.perform(put("/api/sites/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "externalId": "SITE-001",
                                "name": "Updated Office",
                                "city": "Vienna",
                                "address": "New Street 20"
                            }
                            """))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").value("Site not found with ID: 999"));
    }
    @Test
    void getSiteByIdShouldReturnSite() throws Exception {
        Site site = new Site();
        site.setExternalId("SITE-001");
        site.setName("Office");
        site.setCity("Budapest");
        site.setAddress("Main Street 10");

        when(siteService.getSiteById(1L)).thenReturn(site);

        mockMvc.perform(get("/api/sites/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Office"))
                .andExpect(jsonPath("$.city").value("Budapest"))
                .andExpect(jsonPath("$.address").value("Main Street 10"));
    }
}