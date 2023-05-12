package com.myway.entities;

public class Location {

    private int id;
    private String nom;
    private int disponibilite;
    private String type;
    private String description;
    private String image;

    public Location() {
    }

    public Location(int id, String nom, int disponibilite, String type, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.disponibilite = disponibilite;
        this.type = type;
        this.description = description;
        this.image = image;
    }

    public Location(String nom, int disponibilite, String type, String description, String image) {
        this.nom = nom;
        this.disponibilite = disponibilite;
        this.type = type;
        this.description = description;
        this.image = image;
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

    public int getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(int disponibilite) {
        this.disponibilite = disponibilite;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}