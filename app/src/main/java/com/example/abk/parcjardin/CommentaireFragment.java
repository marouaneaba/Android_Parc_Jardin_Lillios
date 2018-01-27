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

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by abakarim on 17/01/18.
 */

public class CommentaireFragment extends DialogFragment {

    private Button Envoyer;
    private RatingBar etoile;
    private EditText Name,Commentaire;

    private String nameS,commentaireS;
    private int nbrEtoileI;

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
                EnvoyerPost();
            }
        });
            setCancelable(true);
        return view;
    }




    public void EnvoyerPost(){

        Service service = URLretrofit();


        nameS = Name.getText().toString().trim();
        commentaireS = Commentaire.getText().toString().trim();
        nbrEtoileI = etoile.getNumStars();

        String responce = service.postCommentaire(nameS,nbrEtoileI,commentaireS);
        if(responce.equals("ok")){
            Toast.makeText(getContext(),"Votre Commentaire il est bien enregitré ",Toast.LENGTH_SHORT).show();
            //getActivity().finish();
        }else if(responce.equals("ko")){
            Toast.makeText(getContext()," Error d'envoyer votre Commentaire !! ",Toast.LENGTH_SHORT).show();
        }
    }
}
