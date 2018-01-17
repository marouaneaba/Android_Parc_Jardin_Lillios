package com.example.abk.parcjardin;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abk.parcjardin.Services.CommentaireFragment;
import com.example.abk.parcjardin.Services.Service;
import com.example.abk.parcjardin.models.ParcJardin;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by abk on 10/01/2018.
 */

public class DetailParcJardin extends AppCompatActivity {


    private TextView text1;
    private TextView text2;
    private Double latitude;
    private Double longitude;
    private TextView Name,Horaire,Addresse,Description;
    private String URL ="https://www.google.com/maps/search/?api=1";
    private List<ParcJardin> parcJardinsP;
    TextView description = null;
    FragmentManager fm ;


    public Service URLretrofit(){
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.ENDPOINT)
                .build()
                .create(Service.class);
        return service;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailparcjardin);

        fm = getSupportFragmentManager();
        text1 = (TextView)findViewById(R.id.latitude);
        text2 = (TextView)findViewById(R.id.logitude);

        Name = (TextView)findViewById(R.id.Name);
        Horaire = (TextView)findViewById(R.id.Horaire);
        Addresse = (TextView)findViewById(R.id.Addresse);
        Description = (TextView)findViewById(R.id.Description);

        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));

        text1.setText(latitude.toString());
        text2.setText(longitude.toString());
        //getParcJardinByLatitudeLongitude(latitude,longitude);
        Actualiser(latitude,longitude);
        getCommentaireJardinParc("NameParcJardin");

    }

    public void Actualiser(double latitude,double longitude){
        Service service = URLretrofit();
        service.getParcJardinLatitudeLongitude(latitude,longitude, new Callback<ParcJardin>() {
            @Override
            public void success(ParcJardin parcJardin, Response response) {
                Name.setText(parcJardin.getName());
                //Horaire.setText(parcJardin.getHoraires().toString());
                Addresse.setText(parcJardin.getAdresse());
                Description.setText(parcJardin.getDescription());
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Error get Détail Parc !!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getCommentaireJardinParc(String NameParcJardin){

        Service service = URLretrofit();
        service.getCommenatiresByParcJardin(NameParcJardin, new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                description = new TextView( DetailParcJardin.this);

                LinearLayout ll = (LinearLayout) findViewById(R.id.liner);
                //ll.setOrientation(LinearLayout.VERTICAL);
                ll.removeAllViews(); //Ligne problèmatique
                ll.removeAllViewsInLayout();
                //for(int i=0;i<4;i++){

                TextView d = new TextView( DetailParcJardin.this);
                d.setText("Marouane Abakarim : ");
                ll.addView(d);

                description.setText("---------hello world--------");
                ll.addView(description); //Autre ligne problèmatique



                //}

            }
        });
    }

    public boolean AjoutCommentaire(View v){
        CommentaireFragment dfragmenet = new CommentaireFragment();
        dfragmenet.show(fm,"Commentaire : ");
        return true;
    }

    /*public List<ParcJardin> getParcJardinByLatitudeLongitude(double latitude,double longitude){

        Service service = URLretrofit();
         return service.getParcJardinLatitudeLongitude(latitude,longitude);
    }*/


    public void iteneraire(View v){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(URL+"&query="+this.latitude+","+this.longitude));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


}
