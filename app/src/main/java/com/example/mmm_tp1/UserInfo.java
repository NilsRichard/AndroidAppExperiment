package com.example.mmm_tp1;

public class UserInfo {

    String nom;
    String prenom;
    String villeNaissance;
    String dateNaissance;
    String tel;

    public UserInfo(String nom, String prenom, String villeNaissance, String dateNaissance, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.villeNaissance = villeNaissance;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getTel() {
        return tel;
    }
}
