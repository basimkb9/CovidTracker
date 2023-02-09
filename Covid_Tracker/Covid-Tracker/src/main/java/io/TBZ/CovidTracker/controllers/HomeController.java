package io.TBZ.CovidTracker.controllers;


import io.TBZ.CovidTracker.Services.CoronaVirusDataService;
import io.TBZ.CovidTracker.models.LocationStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController extends CoronaVirusDataService {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;
    @GetMapping("/")
    public String home(Model model){


        List<LocationStats> allStats_cc = coronaVirusDataService.getAllStats_CC();
        int totalReportedCases = allStats_cc.stream().mapToInt(stat -> stat.getConfirmedCases()).sum();
        int totalNewCases = allStats_cc.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();


        model.addAttribute("locationStats" , allStats_cc);
        model.addAttribute("totalReportedCases" , totalReportedCases);
        model.addAttribute("totalNewCases" , totalNewCases);
//        model.addAttribute("locationStats" , coronaVirusDataService.getAllStats_CD());
//        model.addAttribute("locationStats" , coronaVirusDataService.getAllStats_CR());
//        model.addAttribute("testName" , "TEST");
        return "home";
    }


}
