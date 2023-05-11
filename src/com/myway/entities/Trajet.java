package com.myway.entities;

public class Trajet {

    private int id;
    private String destination;

    public Trajet() {
    }

    public Trajet(int id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public Trajet(String destination) {
        this.destination = destination;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


}