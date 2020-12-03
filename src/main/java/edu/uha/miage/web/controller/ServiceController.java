/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Services;
import edu.uha.miage.core.service.CategorieService;
import edu.uha.miage.core.service.ServiceService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author victo
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    ServiceService serviceService;

    @Autowired
    CategorieService categorieService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        model.addAttribute("services", serviceService.findAll());
        return "service/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Services service = new Services();
        model.addAttribute("service", service);
        model.addAttribute("categories", categorieService.findAll());
        return "service/edit";
    }

    @PostMapping("/create")
    public String created(@Valid Services service, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("categories", categorieService.findAll());
            return "service/edit";
        }
        serviceService.save(service);
        return "redirect:/service";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("service", serviceService.findById(id).get());
        model.addAttribute("categories", categorieService.findAll());
        return "service/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid Services service, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("categories", categorieService.findAll());
            return "service/edit";
        }

        serviceService.save(service);
        return "redirect:/service";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        serviceService.delete(id);
        return "redirect:/service";
    }
}