package com.trendyol.controller;

import com.trendyol.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConfigurationController {

    @Autowired
    ConfigurationService configurationService;

    @RequestMapping(method = RequestMethod.GET, path = "/configurations/active")
    public String getActiveConfigurationsByApplicationName(@RequestParam String applicationName, Model model) {
        model.addAttribute("configurations", configurationService.getActiveConfigurationsByApplicationName(applicationName));
        return "configurations";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/configurations/add")
    public String addConfiguration(@RequestParam String name,
                                   @RequestParam String type,
                                   @RequestParam String value,
                                   @RequestParam Integer status,
                                   @RequestParam String applicationName,
                                   Model model) {
        configurationService.addConfiguration(name, type, value, status, applicationName);
        model.addAttribute("configurations", configurationService.getActiveConfigurationsByApplicationName(applicationName));
        return "configurations";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/configurations/update")
    public String updateConfiguration(@RequestParam Long id,
                                      @RequestParam String name,
                                      @RequestParam String type,
                                      @RequestParam String value,
                                      @RequestParam Integer status,
                                      @RequestParam String applicationName,
                                      Model model) {
        configurationService.updateConfiguration(id, name, type, value, status, applicationName);
        model.addAttribute("configurations", configurationService.getActiveConfigurationsByApplicationName(applicationName));
        return "configurations";
    }
}
