package com.example.abk.parcjardin.models;

/**
 * Created by abk on 04/01/2018.
 */

public class Commentaire {

    private Long id;
    private String name;
    private int nbrEtoile;
    private String commentaire;
    private boolean confirmer;
    private ParcJardin parcJardinn;


    public Commentaire(){}

    public Commentaire(String name, int nbrEtoile, String commentaire, boolean confirmer, ParcJardin parcJardinn) {
        this.name = name;
        this.nbrEtoile = nbrEtoile;
        this.commentaire = commentaire;
        this.confirmer = confirmer;
        this.parcJardinn = parcJardinn;
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

    public int getNbrEtoile() {
        return nbrEtoile;
    }

    public void setNbrEtoile(int nbrEtoile) {
        this.nbrEtoile = nbrEtoile;
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

    public ParcJardin getParcJardinn() {
        return parcJardinn;
    }

    public void setParcJardinn(ParcJardin parcJardinn) {
        this.parcJardinn = parcJardinn;
    }

    @Override
    public String toString() {
        return "Commentaire{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", nbrEtoile=" + nbrEtoile +
                ", commentaire='" + commentaire + '\'' +
                ", confirmer=" + confirmer +
                ", parcJardinn=" + parcJardinn +
                '}';
    }
}
