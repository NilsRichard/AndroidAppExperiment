package com.example.mmm_tp1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.firebase.ui.auth.data.model.User;

public class UserInfo {

    private String uid;

    private String nom;

    private String prenom;

    private String villeNaissance;

    private String dateNaissance;

    private String tel;

    private int likes;

    public UserInfo (){
        // Needed
    }

    public UserInfo(String nom, String prenom, String villeNaissance, String dateNaissance, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.villeNaissance = villeNaissance;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
        this.likes = 0;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getVilleNaissance() {
        return villeNaissance;
    }

    public void setVilleNaissance(String villeNaissance) {
        this.villeNaissance = villeNaissance;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
