package com.example.abk.parcjardin.models;

import java.util.List;

/**
 * Created by abk on 06/01/2018.
 */

public class Categorie {

    private  Long id;
    private List<ParcJardin> parcJardinn;
    private String nomcategorie;


    public Categorie(List<ParcJardin> ParcJardinn,String Nomcategorie){
        this.parcJardinn = ParcJardinn;
        this.nomcategorie = Nomcategorie;
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
        nomcategorie = nomcategorie;
    }

    public List<ParcJardin> getParcJardinn() {
        return parcJardinn;
    }

    public void setParcJardinn(List<ParcJardin> parcJardinn) {
        parcJardinn = parcJardinn;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", ParcJardinn=" + parcJardinn +
                ", Nomcategorie='" + nomcategorie + '\'' +
                '}';
    }
}
