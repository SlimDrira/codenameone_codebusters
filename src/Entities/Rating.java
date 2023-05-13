/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

/**
 *
 * @author 9naydel
 */
public class Rating {
    private int id;
    private int note;
    private Etablissement etablissement;
    
    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", etablissement=" + etablissement + ", note=" + note + '}';
    }

    public int getId() {
        return id;
    }

    public Rating() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Rating(int note, Etablissement etablissement) {
        this.etablissement = etablissement;
        this.note = note;
    }
}
