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
    private String l;
    private String g;
    private String adresse;
<<<<<<< HEAD
    private List<java.lang.String> nameImage;
    private List<Categorie> categories;
=======
    List<Commentaire> commentaire;
    List<Horaire> horaire;
>>>>>>> 5946f64aa151ff23beb5d158df94a1c9b03e5636

    public ParcJardin(){}

<<<<<<< HEAD
    public ParcJardin(String name, String description, String type, String l, String g, String adresse) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.l = l;
        this.g = g;
        this.adresse = adresse;
=======
    public ParcJardin(String name,String description,String type,
                      double L,double G,String addresse,List<Commentaire> commentaire,
                      List<Horaire> horaires){
        this.name = name;
        this.description = description;
        this.type = type;
        this.l = L;
        this.g = G;
        this.adresse = addresse;
        this.commentaire = commentaire;
        this.horaire = horaires;
>>>>>>> 5946f64aa151ff23beb5d158df94a1c9b03e5636
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

<<<<<<< HEAD
    public List<String> getNameImage() {
        return nameImage;
    }

    public void setNameImage(List<String> nameImage) {
        this.nameImage = nameImage;
=======
    public List<Commentaire> getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(List<Commentaire> commentaire) {
        this.commentaire = commentaire;
    }

    public List<Horaire> getHoraire() {
        return horaire;
    }

    public void setHoraire(List<Horaire> horaire) {
        this.horaire = horaire;
>>>>>>> 5946f64aa151ff23beb5d158df94a1c9b03e5636
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
<<<<<<< HEAD
                ", nameImage=" + nameImage +
                ", categories=" + categories +
=======
                ", commentaires=" + commentaire +
                ", horaires=" + horaire +
>>>>>>> 5946f64aa151ff23beb5d158df94a1c9b03e5636
                '}';
    }
}
