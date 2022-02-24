package com.example.gestionscolarite.model;

public class Inscription {

    private int id;
    private String date;
    private Niveau niveau;
    private Filiere filiere;
    private Etudiant etudiant;

    public Inscription() {
    }

    public Inscription(int id, Niveau niveau, Filiere filiere, Etudiant etudiant, String date) {
        this.id = id;
        this.niveau = niveau;
        this.filiere = filiere;
        this.etudiant = etudiant;
        this.date = date;
    }

    public Inscription(Niveau niveau, Filiere filiere, Etudiant etudiant,String date) {
        this.niveau = niveau;
        this.filiere = filiere;
        this.etudiant = etudiant;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public String toString() {
        return "Inscription{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", niveau=" + niveau +
                ", filiere=" + filiere +
                ", etudiant=" + etudiant +
                '}';
    }
}