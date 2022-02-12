package com.example.gestionscolarite.model;

public class Etudiant {
    private int id;
    private String code_etudiant;
    private String nom_etudiant;
    private String prenom_etudiant;
    private String date_naissance;

    public Etudiant() {
    }

    public Etudiant(int id, String code_etudiant, String nom_etudiant, String prenom_etudiant, String date_naissance) {
        this.id = id;
        this.code_etudiant = code_etudiant;
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.date_naissance = date_naissance;
    }

    public Etudiant(String code_etudiant, String nom_etudiant, String prenom_etudiant, String date_naissance) {
        this.code_etudiant = code_etudiant;
        this.nom_etudiant = nom_etudiant;
        this.prenom_etudiant = prenom_etudiant;
        this.date_naissance = date_naissance;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode_etudiant() {
        return code_etudiant;
    }

    public void setCode_etudiant(String code_etudiant) {
        this.code_etudiant = code_etudiant;
    }

    public String getNom_etudiant() {
        return nom_etudiant;
    }

    public void setNom_etudiant(String nom_etudiant) {
        this.nom_etudiant = nom_etudiant;
    }

    public String getPrenom_etudiant() {
        return prenom_etudiant;
    }

    public void setPrenom_etudiant(String prenom_etudiant) {
        this.prenom_etudiant = prenom_etudiant;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", code_etudiant='" + code_etudiant + '\'' +
                ", nom_etudiant='" + nom_etudiant + '\'' +
                ", prenom_etudiant='" + prenom_etudiant + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                '}';
    }
}
