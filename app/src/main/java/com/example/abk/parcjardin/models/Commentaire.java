package com.example.abk.parcjardin.models;

/**
 * Created by abk on 04/01/2018.
 */

public class Commentaire {

    private Long id;
    private String Name;
    private int nbrEtoile;
    private String commentaire;
    private boolean confirmer;



    public Commentaire(String sName,int sNumeEtoile,String sCommentaire,boolean sConfirmer){
        this.Name = sName;
        this.nbrEtoile = sNumeEtoile;
        this.commentaire = sCommentaire;
        this.confirmer = sConfirmer;
    }

    public int getNbrEtoile() {
        return nbrEtoile;
    }

    public void setNbrEtoile(int nbrEtoile) {
        this.nbrEtoile = nbrEtoile;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isConfirmer() {
        return confirmer;
    }

    public void setConfirmer(boolean confirmer) {
        this.confirmer = confirmer;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", commentaire='" + commentaire + '\'' +
                ", confirmer=" + confirmer +", Name="+Name+
                '}';
    }
}
