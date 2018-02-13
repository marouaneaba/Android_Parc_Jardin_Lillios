package com.example.abk.parcjardin.Services;

import com.example.abk.parcjardin.models.Categorie;
import com.example.abk.parcjardin.models.Commentaire;
import com.example.abk.parcjardin.models.Horaire;
import com.example.abk.parcjardin.models.ParcJardin;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by abk on 04/01/2018.
 */

public interface Service {


    public static final String ENDPOINT = "https://obscure-reef-42267.herokuapp.com";





    @GET("/api/PJ")
    void getParcJardinn(Callback<List<ParcJardin>> callback);

    @GET("/api/PJBysearch/{search}")
    void getParcJardinnSearch(@Path("search") String search,Callback<List<ParcJardin>> callback);

    @GET("/api/PJByName/{name}")
    void GetParcJardinByName(@Path("name") String Name,Callback<ParcJardin> callback);

    @GET("/api/PJByservice/{service}")
    void getParcJardinnService(@Path("service") String service,Callback<List<ParcJardin>> callback);



    @GET("/api/CommentaireByPJ/{ParcJardin}")
    void getCommenatiresByParcJardin(@Path("ParcJardin") Long IdParcJardin,Callback<List<Commentaire>> callback);

    @GET("/api/horaire/{idParcJardin}")
    void getHoraireByIdParcJardinLillios(@Path("idParcJardin") Long IdParcJardin,Callback<List<Horaire>> callback);

    @GET("/api/PostCommentaire/{idPJ}/{name}/{nbrEtoile}/{commentaire}")
    void PostCommentaire(@Path("idPJ")Long idPJ,@Path("name")String Name, @Path("nbrEtoile")int nbrEtoile, @Path("commentaire") String commentaire,Callback<Commentaire> callback);


    @GET("/api/Parc/test")
    void getAllParc(Callback<List<ParcJardin>> callback);

    @GET("/api/Jardin/test")
    void getAllJardin(Callback<List<ParcJardin>> callback);

    @GET("/api/imagesParcJardin/{ParcJardin}/test")
    void getImagesParcJardin(@Path("ParcJardin") String ParcJardin,Callback<List<String>> callback);



}
