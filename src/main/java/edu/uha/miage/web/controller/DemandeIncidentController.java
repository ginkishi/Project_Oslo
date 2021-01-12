/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.DemandeIncident;
import edu.uha.miage.core.entity.Incident;
import edu.uha.miage.core.service.DemandeIncidentService;
import edu.uha.miage.core.service.DomaineService;
import edu.uha.miage.core.service.FonctionService;
import edu.uha.miage.core.service.IncidentService;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Kalictong
 */
@Controller
@RequestMapping("/demande")
public class DemandeIncidentController {
    
    @Autowired
    DemandeIncidentService demandeIncidentService;
    
    @Autowired
    IncidentService incidentService;

    @Autowired
    DomaineService domaineService;
    
    @Autowired
    FonctionService fonctionService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        List<DemandeIncident> d = demandeIncidentService.findAll();
        model.addAttribute("demandeIncidents", demandeIncidentService.findAll());
        return "demande/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        DemandeIncident demandeIncident = new DemandeIncident();
        model.addAttribute("demandeIncident", demandeIncident);
        model.addAttribute("incidents", incidentService.findAll());
        model.addAttribute("domaines", domaineService.findAll());
        model.addAttribute("fonctions", fonctionService.findAll());
        return "demande/edit";
    }

    @PostMapping("/create")
    public String created(@Valid DemandeIncident demandeIncident, BindingResult br, Model model) {

        if (br.hasErrors()) {
            model.addAttribute("categories", domaineService.findAll());
            return "demande/edit";
        }
        demandeIncidentService.save(demandeIncident);
        return "redirect:/demande";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        model.addAttribute("demandeIncident", demandeIncidentService.findById(id).get());
        model.addAttribute("incidents", incidentService.findAll());
        model.addAttribute("domaines", domaineService.findAll());
        model.addAttribute("fonctions", fonctionService.findAll());
        return "demande/edit";
    }

    @PostMapping("/edit")
    public String edited(@Valid DemandeIncident demandeIncident, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("domaine", domaineService.findAll());
            return "demande/edit";
        }

        demandeIncidentService.save(demandeIncident);
        return "redirect:/demande";
    }

    @GetMapping("/cloture/{id}")
    public String cloture(@PathVariable("id") Long id) {
        demandeIncidentService.cloture(id);
        return "redirect:/demande";
    }
}