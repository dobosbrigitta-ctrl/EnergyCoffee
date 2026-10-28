
package com.energycoffee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.energycoffee.service.EnergyRecordService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final EnergyRecordService energyRecordService;

    public HomeController(EnergyRecordService energyRecordService) {
        this.energyRecordService = energyRecordService;
    }
    @PostMapping("/records")
    public String saveRecord(@RequestParam Double consumption) {
        energyRecordService.saveConsumption(consumption);
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

        model.addAttribute("consumption", consumption);
        model.addAttribute("estimatedCost", estimatedCost);
        model.addAttribute("highConsumption", highConsumption);

        return "dashboard";
    }
}
