package com.example.abk.parcjardin.models;

import java.util.List;

/**
 * Created by abk on 04/01/2018.
 */

public class ParcJardin {

    private Long id;
    private String name;
    private String description;
    private String type;  // parc ou jardin
    private double l;
    private double g;
    private String adresse;
    List<Commentaire> commentaires;
    List<Horaire> horaire;


    public ParcJardin(String name,String description,String type,
                      double L,double G,String addresse,List<Commentaire> commentaires,
                      List<Horaire> horaires){
        this.name = name;
        this.description = description;
        this.type = type;
        this.l = L;
        this.g = G;
        this.adresse = addresse;
        this.commentaires = commentaires;
        this.horaire = horaires;
    }


    public Long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public double getL() {
        return l;
    }

    public double getG() {
        return g;
    }

    public String getAdresse() {
        return adresse;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setL(double l) {
        l = l;
    }

    public void setG(double g) {
        g = g;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public List<Horaire> getHoraires() {
        return horaire;
    }

    public void setHoraires(List<Horaire> horaires) {
        this.horaire = horaires;
    }

    @Override
    public String toString() {
        return "ParcJardin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", L=" + l +
                ", G=" + g +
                ", adresse='" + adresse + '\'' +
                ", commentaires=" + commentaires +
                ", horaires=" + horaire +
                '}';
    }
}
