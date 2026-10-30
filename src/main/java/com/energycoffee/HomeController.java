package com.energycoffee;

import com.energycoffee.model.Site;
import com.energycoffee.service.EnergyRecordService;
import com.energycoffee.service.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private final EnergyRecordService energyRecordService;
    private final SiteService siteService;

    public HomeController(
            EnergyRecordService energyRecordService,
            SiteService siteService) {
        this.energyRecordService = energyRecordService;
        this.siteService = siteService;
    }

    @PostMapping("/records")
    public String saveRecord(
            @RequestParam Double consumption,
            @RequestParam Long siteId) {

        energyRecordService.saveConsumption(consumption, siteId);
        return "redirect:/";
    }

    @GetMapping("/")
    public String dashboard(
            @RequestParam(defaultValue = "800") int consumption,
            Model model) {

        int consumptionThreshold = 1000;
        boolean highConsumption = consumption > consumptionThreshold;

        double pricePerKwh = 0.20;
        double estimatedCost = consumption * pricePerKwh;

        List<Site> sites = siteService.getAllSites();

        model.addAttribute("consumption", consumption);
        model.addAttribute("estimatedCost", estimatedCost);
        model.addAttribute("highConsumption", highConsumption);
        model.addAttribute("sites", sites);

        return "dashboard";
    }
}