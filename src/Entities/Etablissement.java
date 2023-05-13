/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import java.util.Date;

/**
 *
 * @author 9naydel
 */
public class Etablissement {
    private int id;
    private String nom, type, description, adresse, image, email, horaires;
    private int views;
    
    private int telephone;
    private Date date_creation ;
    private Trajet trajet;
    

    public Etablissement(String nom, String type, String description, String adresse, String image, String email, String horaires, int views, Date date_creation, int telephone, Trajet trajet) {
        this.nom = nom;
        this.type = type;
        this.description = description;
        this.adresse = adresse;
        this.image = image;
        this.email = email;
        this.horaires = horaires;
        this.views = views;
        this.date_creation = date_creation;
        this.telephone = telephone;
        this.trajet = trajet;
    }

    public Etablissement() {
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", description=" + description + ", adresse=" + adresse + ", image=" + image + ", email=" + email + ", horaires=" + horaires + ", views=" + views + ", date_creation=" + date_creation + ", telephone=" + telephone + ", trajet=" + trajet + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoraires() {
        return horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }
    
}
