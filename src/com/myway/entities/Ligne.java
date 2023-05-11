package com.myway.entities;

public class Ligne {

    private int id;
    private Trajet trajet;
    private Moyentp moyentp;

    public Ligne() {
    }

    public Ligne(int id, Trajet trajet, Moyentp moyentp) {
        this.id = id;
        this.trajet = trajet;
        this.moyentp = moyentp;
    }

    public Ligne(Trajet trajet, Moyentp moyentp) {
        this.trajet = trajet;
        this.moyentp = moyentp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trajet getTrajet() {
        return trajet;
    }

    public void setTrajet(Trajet trajet) {
        this.trajet = trajet;
    }

    public Moyentp getMoyentp() {
        return moyentp;
    }

    public void setMoyentp(Moyentp moyentp) {
        this.moyentp = moyentp;
    }


}