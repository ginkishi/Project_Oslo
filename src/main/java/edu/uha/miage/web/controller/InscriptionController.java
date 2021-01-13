/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.web.controller;

import edu.uha.miage.core.entity.Compte;
import edu.uha.miage.core.entity.Personne;
import edu.uha.miage.core.service.CompteService;
import edu.uha.miage.core.service.DepartementService;

import edu.uha.miage.core.service.PersonneService;
import edu.uha.miage.core.service.RoleService;

import edu.uha.miage.model.Inscription;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author victo
 */

//@Profile("prod")
@Controller
@RequestMapping("/inscription")
public class InscriptionController {
    
    @Autowired
    PersonneService personneService;
    
     @Autowired
    DepartementService departementService;
    
    @Autowired
    CompteService compteService;
    
    @Autowired
    RoleService roleService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String inscription(Model model) {
        model.addAttribute("inscription", new Inscription());
        model.addAttribute("departements", departementService.findAll());
        return "inscription2";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String inscrit(@Valid Inscription inscription,BindingResult br) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //Users user = new Users(inscription.getUsername(), "{noop}"+inscription.getPassword());
        
         if (br.hasErrors()) {
            return "inscription2";
        }
        
        Personne user = new Personne(inscription.getNom(), inscription.getPrenom(),inscription.getAdresse(),inscription.getEmail());
        personneService.save(user);

        Compte compte = new Compte(inscription.getUsername(),encoder.encode(inscription.getPassword()), user,roleService.findByLibelle("ROLE_COLLABORATEUR"));
        compteService.save(compte);
        return "redirect:/login";
    }
}
