package com.example.abk.parcjardin.Services;

import com.example.abk.parcjardin.models.Categorie;
import com.example.abk.parcjardin.models.Commentaire;
import com.example.abk.parcjardin.models.Horaire;
import com.example.abk.parcjardin.models.ParcJardin;
import com.example.abk.parcjardin.models.Repo;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;
import retrofit.Callback;

/**
 * Created by abk on 04/01/2018.
 */

public interface Service {

    public static final String ENDPOINT = "http://env-2321100.hidora.com";//"https://api.github.com";


    @GET("/api/Categorie")
    void getCategorie(Callback<List<Categorie>> callback);

    @GET("/api/PJ")
    void getParcJardinn(Callback<List<ParcJardin>> callback);

    @GET("/api/PJBysearch/{search}")
    void getParcJardinnSearch(@Path("search") String search,Callback<List<ParcJardin>> callback);

    @GET("/api/PJByservice/{service}")
    void getParcJardinnService(@Path("service") String service,Callback<List<ParcJardin>> callback);

    @GET("/api/PJBylocalisation/{Latitude}/{Longitude}")
    void getParcJardinLatitudeLongitude(@Path("Latitude") double Latitude,@Path("Longitude")double Longitude,Callback<ParcJardin> callback);

	@GET("/api/categorieByPJ/{parcJardinn}")
	void getCategorieByParcJardinn(@Path("parcJardinn") String ParcJardin,Callback<List<Categorie>> callback);

     @GET("/api/CommentairesByParcJardinn/{parcJardin}")
     void getCommenatiresByParcJardin(@Path("parcJardinn") String ParcJardin,Callback<List<Commentaire>> callback);

    @POST("/api/commentaireAjouter")
    @FormUrlEncoded
    String postCommentaire(@Field("name")String Name, @Field("nbrEtoile")int nbrEtoile, @Field("commenaire") String commentaire);

    @GET("/api/Parc")
    void getAllParc(Callback<List<ParcJardin>> callback);

    @GET("/api/Jardin")
    void getAllJardin(Callback<List<ParcJardin>> callback);

    @GET("/api/imagesParcJardin/{ParcJardin}")
    void getImagesParcJardin(@Path("ParcJardin") String ParcJardin,Callback<List<String>> callback);
    /*
    *@GET("/api/para")
    * List searchRepos(@Query("g") String query) ==> /api/para?q=picasso
    * */
    /**/
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
