package edu.uha.miage.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Quentin
 */
@Entity
public class Incident implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    @Size(min = 2, max = 50)

    private String libelle;
    
    @NotNull
    @Min(1)
    @Max(10)
    private int priorite;

    @ManyToOne
    private Domaine domaine;
    

    /*@ManyToMany(mappedBy = "typeIncidents")
    List<Fonction> fonctions;*/

    @OneToOne(mappedBy = "incident")
    private DemandeIncident demande_incident;
    
    @ManyToMany
    @JoinTable(
        name = "fonction_occupe_incident", 
        joinColumns = @JoinColumn(name = "incident_id"), 
        inverseJoinColumns = @JoinColumn(name = "fonction_id"))
    private List<Fonction> fonctionOccupe;

    
    public Incident() {}
    
    public Incident(String libelle, int priorite, Domaine domaine) {
        this.libelle = libelle;
        this.priorite = priorite;
        this.domaine = domaine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    
    @Override
    public String toString() {
        return this.libelle;
    }

   /* public List<Fonction> getFonctions() {
        return fonctions;
    }

    public void setFonctions(List<Fonction> fonctions) {
        this.fonctions = fonctions;
*/

    public List<Fonction> getFonctionOccupe() {
        return fonctionOccupe;
    }

    public void setFonctionOccupe(List<Fonction> fonctionOccupe) {
        this.fonctionOccupe = fonctionOccupe;
    }

    public DemandeIncident getDemande_incident() {
        return demande_incident;
    }

    public void setDemande_incident(DemandeIncident demande_incident) {
        this.demande_incident = demande_incident;

    }
    
    
}
