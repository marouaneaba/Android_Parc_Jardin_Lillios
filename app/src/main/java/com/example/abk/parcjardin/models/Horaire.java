package com.example.abk.parcjardin.models;

/**
 * Created by abk on 06/01/2018.
 */

public class Horaire {

    private Long id;
    private String ouverture;
    private String fermuture;
    private String journee;


    public Horaire(String ouverture,String fermuture,String journee){
        this.ouverture = ouverture;
        this.fermuture = fermuture;
        this.journee = journee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFermuture() {
        return fermuture;
    }

    public void setFermuture(String fermuture) {
        this.fermuture = fermuture;
    }

    public String getJournee() {
        return journee;
    }

    public void setJournee(String journee) {
        this.journee = journee;
    }

    public String getOuverture() {
        return ouverture;
    }

    public void setOuverture(String ouverture) {
        this.ouverture = ouverture;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "id=" + id +
                ", ouverture='" + ouverture + '\'' +
                ", fermuture='" + fermuture + '\'' +
                ", journee='" + journee + '\'' +
                '}';
    }
}
