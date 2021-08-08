package com.company.coronavirustracker.controllers;

import com.company.coronavirustracker.models.LocationStats;
import com.company.coronavirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

     @Autowired
     CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allstats= coronaVirusDataService.getAllStats();
        int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        model.addAttribute("locationStats", allstats);
        model.addAttribute("totalReportedCases", totalReportedCases);

        return "home";
    }
}
