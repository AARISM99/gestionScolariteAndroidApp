package com.example.gestionscolarite.model;

public class Filiere {

    private int id;
    private String code_filiere;
    private String nom_filiere;
    private Niveau niveau;

    public Filiere() {
    }

    public Filiere(int id, String code_filiere, String nom_filiere, Niveau niveau) {
        this.id = id;
        this.code_filiere = code_filiere;
        this.nom_filiere = nom_filiere;
        this.niveau = niveau;
    }

    public Filiere(String code_filiere, String nom_filiere, Niveau niveau) {
        this.code_filiere = code_filiere;
        this.nom_filiere = nom_filiere;
        this.niveau = niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode_filiere() {
        return code_filiere;
    }

    public void setCode_filiere(String code_filiere) {
        this.code_filiere = code_filiere;
    }

    public String getNom_filiere() {
        return nom_filiere;
    }

    public void setNom_filiere(String nom_filiere) {
        this.nom_filiere = nom_filiere;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Filiere{" +
                "id=" + id +
                ", code_filiere='" + code_filiere + '\'' +
                ", nom_filiere='" + nom_filiere + '\'' +
                ", niveau=" + niveau +
                '}';
    }
}
