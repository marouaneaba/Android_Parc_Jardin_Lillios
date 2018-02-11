package com.example.abk.parcjardin.models;

import java.util.List;

/**
 * Created by abk on 04/01/2018.
 */

public class ParcJardin {


    private Long id;
    private java.lang.String name;
    private java.lang.String description;
    private String type;  // parc ou jardin
    private String l;
    private String g;
    private String adresse;
    private List<java.lang.String> NameImage;
    private List<Categorie> categories;


    public ParcJardin(){}

    public ParcJardin(String name, String description, String type, String l, String g, String adresse) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.l = l;
        this.g = g;
        this.adresse = adresse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<String> getNameImage() {
        return NameImage;
    }

    public void setNameImage(List<String> nameImage) {
        NameImage = nameImage;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "ParcJardin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", l='" + l + '\'' +
                ", g='" + g + '\'' +
                ", adresse='" + adresse + '\'' +
                ", NameImage=" + NameImage +
                ", categories=" + categories +
                '}';
    }
}
