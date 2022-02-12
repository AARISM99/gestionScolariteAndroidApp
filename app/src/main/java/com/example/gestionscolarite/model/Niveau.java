package com.example.gestionscolarite.model;

public class Niveau {
    private int id;
    private String code_niveau;
    private String titre_niveau;


    //les constructeurs
    public Niveau(int id, String code_niveau, String titre_niveau) {
        this.id = id;
        this.code_niveau = code_niveau;
        this.titre_niveau = titre_niveau;
    }

    public Niveau(String code_niveau, String titre_niveau) {
        this.code_niveau = code_niveau;
        this.titre_niveau = titre_niveau;
    }

    public Niveau() {
    }


    //les getters et les setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode_niveau() {
        return code_niveau;
    }

    public void setCode_niveau(String code_niveau) {
        this.code_niveau = code_niveau;
    }

    public String getTitre_niveau() {
        return titre_niveau;
    }

    public void setTitre_niveau(String titre_niveau) {
        this.titre_niveau = titre_niveau;
    }

    @Override
    public String toString() {
        return "Niveau{" +
                "id=" + id +
                ", code_niveau='" + code_niveau + '\'' +
                ", titre_niveau='" + titre_niveau + '\'' +
                '}';
    }
}