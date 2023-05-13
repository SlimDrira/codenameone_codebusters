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
public class Trajet {
    private int id;
    private String depart, destination, etat, directions;
    private float latitude, longitude, distance;
    private int views;
    private Date date_creation ;

    public Trajet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    @Override
    public String toString() {
        return "Trajet{" + "id=" + id + ", depart=" + depart + ", destination=" + destination + ", etat=" + etat + ", directions=" + directions + ", latitude=" + latitude + ", longitude=" + longitude + ", distance=" + distance + ", views=" + views + ", date_creation=" + date_creation + '}';
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
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

    public Trajet(String depart, String destination, String etat, String directions, float latitude, float longitude, float distance, int views, Date date_creation) {
        this.depart = depart;
        this.destination = destination;
        this.etat = etat;
        this.directions = directions;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
        this.views = views;
        this.date_creation = date_creation;
    }
    
    
}
