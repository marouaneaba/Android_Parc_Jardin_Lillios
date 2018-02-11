package com.example.abk.parcjardin.models;

import java.util.List;

/**
 * Created by abk on 06/01/2018.
 */

public class Categorie {

    private  Long id;
    private String nomcategorie;



    public Categorie(){}

    public Categorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", nomcategorie='" + nomcategorie + '\'' +
                '}';
    }
}
