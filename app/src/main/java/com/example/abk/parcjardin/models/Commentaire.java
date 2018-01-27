package com.example.abk.parcjardin.models;

/**
 * Created by abk on 04/01/2018.
 */

public class Commentaire {

    private Long id;
    private String commentaire;
    private boolean confirmer;
    private String Name;


    public Commentaire(String commentaire,boolean confirmer){
        this.commentaire = commentaire;
        this.confirmer = confirmer;
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
