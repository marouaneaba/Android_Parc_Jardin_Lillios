package com.example.abk.parcjardin.Services;

import com.example.abk.parcjardin.models.Categorie;
import com.example.abk.parcjardin.models.Commentaire;
import com.example.abk.parcjardin.models.Horaire;
import com.example.abk.parcjardin.models.ParcJardin;
import com.example.abk.parcjardin.models.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.Callback;

/**
 * Created by abk on 04/01/2018.
 */

public interface Service {

    public static final String ENDPOINT = "http://env-4431942.hidora.com";//"https://api.github.com";


    @GET("/api/Categorie")
    void getCategorie(Callback<List<Categorie>> callback);

    @GET("/api/PJ")
    void getParcJardinn(Callback<List<ParcJardin>> callback);

    @GET("/api/PJ/{search}")
    void getParcJardinnSearch(Callback<List<ParcJardin>> callback);

    @GET("/api/Commentaire")
    void getCommentaire(Callback<List<Commentaire>> callback);

    @GET("/api/Commentaire")
    void getHoraire(Callback<List<Horaire>> callback);


    @POST("/api/commentaire")
    void postCommentaire(@Field("description") String description, @Field("valider") boolean valider, Callback<List<Repo>> callback);

    @POST("/api/PJ")
    void  postParcJardinn(@Field("name")String name,@Field("description")String description,
                            @Field("type")String type,@Field("l")double l,@Field("g")double g,
                            @Field("addresse")String addresse);



}
