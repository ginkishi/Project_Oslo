package edu.uha.miage;

import edu.uha.miage.core.entity.Categorie;
import edu.uha.miage.core.entity.Compte;
import edu.uha.miage.core.entity.Domaine;
import edu.uha.miage.core.entity.Personne;
import edu.uha.miage.core.entity.Role;
import edu.uha.miage.core.entity.StatutDemande;
import edu.uha.miage.core.service.CategorieService;
import edu.uha.miage.core.service.CompteService;
import edu.uha.miage.core.service.DomaineService;
import edu.uha.miage.core.service.PersonneService;
import edu.uha.miage.core.service.RoleService;
import edu.uha.miage.core.service.StatutDemandeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Quentin
 */
@Order(3)
@Component
public class DemoCategorie implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDepartement.class);

    @Autowired
    private CategorieService categorieService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

        createCategorie("Informatique", null);
        createCategorie("RH", null);
        createCategorie("Périphérique & imprimante", categorieService.findByLibelle("Informatique"));
    }

    private void createCategorie(String name, Categorie parent) {
        Categorie cpt = categorieService.findByLibelle(name);
        if (cpt == null) {

            Categorie c = new Categorie(name, parent);

            categorieService.save(c);
            LOGGER.info("BDD DEMO - Création du domaine " + name);
        } else {
            LOGGER.info("BDD DEMO - Le domaine " + name + " existait déjà");
        }
    }
}
