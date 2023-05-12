package com.myway.entities;

import com.myway.utils.DateUtils;
import com.myway.utils.Statics;

import java.util.Date;

public class Contrat implements Comparable<Contrat> {

    private int id;
    private Location location;
    private float prix;
    private Date dateDebut;
    private Date dateFin;

    public Contrat() {
    }

    public Contrat(int id, Location location, float prix, Date dateDebut, Date dateFin) {
        this.id = id;
        this.location = location;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Contrat(Location location, float prix, Date dateDebut, Date dateFin) {
        this.location = location;
        this.prix = prix;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }


    @Override
    public int compareTo(Contrat contrat) {
        switch (Statics.compareVar) {
            case "Location":
                return this.getLocation().getNom().compareTo(contrat.getLocation().getNom());
            case "Prix":
                return Float.compare(this.getPrix(), contrat.getPrix());
            case "DateDebut":
                DateUtils.compareDates(this.getDateDebut(), contrat.getDateDebut());
            case "DateFin":
                DateUtils.compareDates(this.getDateFin(), contrat.getDateFin());

            default:
                return 0;
        }
    }

}