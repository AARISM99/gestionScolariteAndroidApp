package com.example.gestionscolarite.model;

public class Evaluation {

    private int id;
    private Etudiant etudiant;
    private Module module;
    private Filiere filiere;
    private float note;

    public Evaluation() {
    }

    public Evaluation(int id, Etudiant etudiant, Module module, Filiere filiere, float note) {
        this.id = id;
        this.etudiant = etudiant;
        this.module = module;
        this.filiere = filiere;
        this.note = note;
    }

    public Evaluation(Etudiant etudiant, Module module, Filiere filiere, float note) {
        this.etudiant = etudiant;
        this.module = module;
        this.filiere = filiere;
        this.note = note;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", etudiant=" + etudiant +
                ", module=" + module +
                ", filiere=" + filiere +
                ", note=" + note +
                '}';
    }
}
