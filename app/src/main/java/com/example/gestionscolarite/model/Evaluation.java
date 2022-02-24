package com.example.gestionscolarite.model;

public class Evaluation {

    private int id;
    private Etudiant etudiant;
    private Module module;
    private float note;
    private String date_evaluation;

    public Evaluation() {

    }

    public Evaluation(int id, Etudiant etudiant, Module module, float note, String date_evaluation) {
        this.id = id;
        this.etudiant = etudiant;
        this.module = module;
        this.note = note;
        this.date_evaluation = date_evaluation;
    }

    public Evaluation(Etudiant etudiant, Module module, float note, String date_evaluation) {
        this.etudiant = etudiant;
        this.module = module;
        this.note = note;
        this.date_evaluation = date_evaluation;
    }

    public int getId() {
        return id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Module getModule() {
        return module;
    }

    public float getNote() {
        return note;
    }

    public String getDate_evaluation() {
        return date_evaluation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public void setDate_evaluation(String date_evaluation) {
        this.date_evaluation = date_evaluation;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", etudiant=" + etudiant +
                ", module=" + module +
                ", note=" + note +
                ", date_evaluation='" + date_evaluation + '\'' +
                '}';
    }
}
