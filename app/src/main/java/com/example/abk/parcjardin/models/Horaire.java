package com.example.abk.parcjardin.models;



public class Horaire {

    private Long id;
    private String ouverture;
    private String fermuture;
    private String journee;
    private ParcJardin parcJardin;


    public Horaire(){}

    public Horaire(String ouverture, String fermuture, String journee, ParcJardin parcJardin) {
        this.ouverture = ouverture;
        this.fermuture = fermuture;
        this.journee = journee;
        this.parcJardin = parcJardin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOuverture() {
        return ouverture;
    }

    public void setOuverture(String ouverture) {
        this.ouverture = ouverture;
    }

    public String getJournee() {
        return journee;
    }

    public void setJournee(String journee) {
        this.journee = journee;
    }

    public ParcJardin getParcJardin() {
        return parcJardin;
    }

    public void setParcJardin(ParcJardin parcJardin) {
        this.parcJardin = parcJardin;
    }

    public String getFermuture() {
        return fermuture;
    }

    public void setFermuture(String fermuture) {
        this.fermuture = fermuture;
    }

    @Override
    public String toString() {
        return "Horaire{" +
                "id=" + id +
                ", ouverture='" + ouverture + '\'' +
                ", fermuture='" + fermuture + '\'' +
                ", journee='" + journee + '\'' +
                ", parcJardin=" + parcJardin +
                '}';
    }
}
