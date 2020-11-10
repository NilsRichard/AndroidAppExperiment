package com.example.mmm_tp1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_info")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "prenom")
    private String prenom;

    @ColumnInfo(name = "villeNaissance")
    private String villeNaissance;

    @ColumnInfo(name = "dateNaissance")
    private String dateNaissance;

    @ColumnInfo(name = "tel")
    private String tel;

    public UserInfo(String nom, String prenom, String villeNaissance, String dateNaissance, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.villeNaissance = villeNaissance;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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
