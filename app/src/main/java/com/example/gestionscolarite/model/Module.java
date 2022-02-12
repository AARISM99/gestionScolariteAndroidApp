package com.example.gestionscolarite.model;

public class Module {

    private int id;
    private String code_module;
    private String titre_module;
    private Niveau niveau;
    private Filiere filiere;

    public Module() {
    }

    public Module(int id, String code_module, String titre_module, Niveau niveau, Filiere filiere) {
        this.id = id;
        this.code_module = code_module;
        this.titre_module = titre_module;
        this.niveau = niveau;
        this.filiere = filiere;
    }

    public Module(String code_module, String titre_module, Niveau niveau, Filiere filiere) {
        this.id = id;
        this.code_module = code_module;
        this.titre_module = titre_module;
        this.niveau = niveau;
        this.filiere = filiere;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode_module() {
        return code_module;
    }

    public void setCode_module(String code_module) {
        this.code_module = code_module;
    }

    public String getTitre_module() {
        return titre_module;
    }

    public void setTitre_module(String titre_module) {
        this.titre_module = titre_module;
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

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", code_module='" + code_module + '\'' +
                ", titre_module='" + titre_module + '\'' +
                ", niveau=" + niveau +
                ", filiere=" + filiere +
                '}';
    }
}
