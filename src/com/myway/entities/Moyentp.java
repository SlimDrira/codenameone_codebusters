package com.myway.entities;

public class Moyentp {

    private int id;
    private String matricule;
    private String type;
    private int nbreplace;
    private float prixTicket;
    private String horaire;
    private String nom;
    private int note;

    public Moyentp() {
    }

    public Moyentp(int id, String matricule, String type, int nbreplace, float prixTicket, String horaire, String nom, int note) {
        this.id = id;
        this.matricule = matricule;
        this.type = type;
        this.nbreplace = nbreplace;
        this.prixTicket = prixTicket;
        this.horaire = horaire;
        this.nom = nom;
        this.note = note;
    }

    public Moyentp(String matricule, String type, int nbreplace, float prixTicket, String horaire, String nom, int note) {
        this.matricule = matricule;
        this.type = type;
        this.nbreplace = nbreplace;
        this.prixTicket = prixTicket;
        this.horaire = horaire;
        this.nom = nom;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbreplace() {
        return nbreplace;
    }

    public void setNbreplace(int nbreplace) {
        this.nbreplace = nbreplace;
    }

    public float getPrixTicket() {
        return prixTicket;
    }

    public void setPrixTicket(float prixTicket) {
        this.prixTicket = prixTicket;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    


}