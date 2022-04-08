package com.example.firstapp.model;

import java.util.LinkedList;

public class Professeur {
    private String nom;
    private String prenom;
    private String tel;
    //private LinkedList<Groupe> liste_groupe;
    private String photo;
    private String departement;

    public Professeur(String nom, String prenom, String tel, String photo, String departement){
        this.nom= new String(nom);
        this.prenom= new String(prenom);
        this.tel= new String(tel);
        this.photo= new String(photo);
        this.departement= new String(departement);
        //this.liste_groupe=liste_groupe;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /*public LinkedList<Groupe> getListe_groupe() {
        return liste_groupe;
    }

    public void setListe_groupe(LinkedList<Groupe> liste_groupe) {
        this.liste_groupe = liste_groupe;
    }
*/
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }
}

