package com.example.abk.parcjardin;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.abk.parcjardin.R;
import com.example.abk.parcjardin.Services.Service;
import com.example.abk.parcjardin.models.Commentaire;
import com.example.abk.parcjardin.models.ParcJardin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by abakarim on 17/01/18.
 */

public class CommentaireFragment extends DialogFragment {

    private Button Envoyer;
    private RatingBar etoile;
    private EditText Name,Commentaire;

    private String nameS,commentaireS;
    private int nbrEtoileI;
    private Long idparcjardinlillios;

    public Service URLretrofit(){
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.ENDPOINT)
                .build()
                .create(Service.class);
        return service;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.ajout_commentaire,null);

        Envoyer = (Button)view.findViewById(R.id.Envoyer);
        etoile = (RatingBar)view.findViewById(R.id.ratingBar2);
        Name = (EditText)view.findViewById(R.id.Name);
        Commentaire = (EditText)view.findViewById(R.id.Commentaire);

        Envoyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Name.getText().toString().trim().equals("") && !Commentaire.getText().toString().trim().equals("") ){
                    EnvoyerPost();
                    Name.setText("");
                    Commentaire.setText("");
                }
                else
                    Toast.makeText(getContext()," Veuillez remplir les cases !!",Toast.LENGTH_SHORT).show();
            }
        });

        setCancelable(true);

        return view;
    }


    public void setIdParcJardinLillios(Long idparcjardinlillios){
        this.idparcjardinlillios = idparcjardinlillios;
    }

    public void EnvoyerPost(){

        Service service = URLretrofit();


        nameS = Name.getText().toString().trim();
        commentaireS = Commentaire.getText().toString().trim().replaceAll("\n"," ");

        nbrEtoileI = etoile.getNumStars();

        service.PostCommentaire(idparcjardinlillios,nameS,nbrEtoileI,commentaireS, new Callback<Commentaire>() {
            @Override
            public void success(Commentaire c, Response response) {
                Toast.makeText(getContext(),"Ton commentaire et bien Envoyer !!",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getContext(),"Error Envoyer Cmmenataire !! : "+error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
