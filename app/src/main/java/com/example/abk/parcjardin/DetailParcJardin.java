package com.example.abk.parcjardin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abk.parcjardin.Services.CommentaireFragment;
import com.example.abk.parcjardin.Services.Service;
import com.example.abk.parcjardin.models.ParcJardin;

import java.util.ArrayList;
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
        getImage();
        getCategorie();

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
    public void getCategorie(){
        List<String> categories = new ArrayList<String>();
        categories.add("sport");
        categories.add("etude");
        categories.add("restauration");

        Service service = URLretrofit();
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.imgCategories);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 20, 10, 5);
        //layoutParams.weight=80;
        layoutParams.width = 80;
        layoutParams.height=80;
        //ll.setOrientation(LinearLayout.VERTICAL);
        ll2.removeAllViews(); //Ligne problèmatique
        ll2.removeAllViewsInLayout();
        for(int i=0;i<categories.size();i++){
            ImageView img = new ImageView(DetailParcJardin.this);

            if(categories.get(i).equals("sport")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.footing));
            }
            if(categories.get(i).equals("etude")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.etude2));
            }
            if(categories.get(i).equals("restauration")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.restauration));
            }
            if(categories.get(i).equals("Parc")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.parc));
            }
            if(categories.get(i).equals("Jardin")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.jardin));
            }
            if(categories.get(i).equals("Promoner")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.promoner));
            }
            if(categories.get(i).equals("ecouter")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.music));
            }
            if(categories.get(i).equals("observer")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.observer));
            }

            /*img.setMaxWidth(1);
            img.layout(1,1,1,1);

            img.setLayoutParams(new FrameLayout.LayoutParams(100,100));*/
            //img.setScaleType(ImageView.ScaleType.FIT_START);
            ll2.addView(img,layoutParams);
        }
    }
    public void getImage(){
        Service service = URLretrofit();
        LinearLayout ll2 = (LinearLayout) findViewById(R.id.imgLinear);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 20, 10, 50);
        //ll.setOrientation(LinearLayout.VERTICAL);
        ll2.removeAllViews(); //Ligne problèmatique
        ll2.removeAllViewsInLayout();
        for(int i=0;i<8;i++){
            ImageView img = new ImageView(DetailParcJardin.this);
            img.setImageDrawable(getResources().getDrawable(R.drawable.man2));
            img.setLayoutParams(new FrameLayout.LayoutParams(100,100));
            img.setMaxWidth(2);
            //img.setScaleType(ImageView.ScaleType.FIT_START);
            ll2.addView(img,layoutParams);
        }


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

                LinearLayout ll2 = (LinearLayout) findViewById(R.id.liner);
                //ll.setOrientation(LinearLayout.VERTICAL);
                ll2.removeAllViews(); //Ligne problèmatique
                ll2.removeAllViewsInLayout();

                for(int i=0;i<4;i++){


                    /*LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    //left,top,right,bottom
                    params.setMargins(0, 0, 600, 0);*/

                    LinearLayout linearH = new LinearLayout(DetailParcJardin.this);
                    linearH.setOrientation(LinearLayout.HORIZONTAL);
                    //linearH.setBackgroundResource(R.drawable.return_com1_mar);
                    //linearH.setBackgroundResource(R.drawable.back_comm_tra);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    //layoutParams.setMargins(0, 0, 0, 100);

                    ImageView img = new ImageView(DetailParcJardin.this);
                    img.setImageDrawable(getResources().getDrawable(R.drawable.profil1));
                    img.setLayoutParams(new FrameLayout.LayoutParams(100,100));

                    img.setScaleType(ImageView.ScaleType.FIT_START);
                    linearH.addView(img);

                    LinearLayout linear = new LinearLayout(DetailParcJardin.this);
                    linear.setOrientation(LinearLayout.VERTICAL);

                    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams2.setMargins(20, 20, 0, 0);

                    linear.removeAllViews(); //Ligne problèmatique
                    linear.removeAllViewsInLayout();

                    TextView d = new TextView( DetailParcJardin.this);
                    d.setText("Marouane Abakarim : ");
                    d.setRight(600);
                    linear.addView(d,layoutParams2);

                    RatingBar rating = new RatingBar(DetailParcJardin.this);
                    rating.setScaleX(0.5f);
                    rating.setScaleY(0.4f);
                    rating.setNumStars(5);
                    rating.setEnabled(false);
                    linear.addView(rating);

                    //params.setMargins(10, 20, 30, 40);

                    /**/

                    /**/
                    /*LinearLayout linearCommentaire = new LinearLayout(DetailParcJardin.this);
                    linearCommentaire.setOrientation(LinearLayout.HORIZONTAL);*/
                    TextView d2 = new TextView( DetailParcJardin.this);
                    d2.setText("c o m m e a n t a i r e c o m m e a n t a i r e c o m m e a n t ai r e" +
                            "c o m m e a nt a i r e c o m m e a n t a i r e c o m m e a n t ai r e " +
                            "c o m m e a nt a i r e  c o m m e a n t a ir e  c o m m e a n t a i r e" +
                            "commeantaire commeantaire commeantaire" +
                            "commeantaire commeantaire commeantaire" +
                            "commeantaire commeantaire commeantaire ");

                    d2.setPadding(40,0,1,0);

                    //d2.setPadding(1,0,0,0);
                    LinearLayout.LayoutParams layoutParamsTextCOmmentaire = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParamsTextCOmmentaire.setMargins(0,20,0,0);
                    //l,t,r,b
                    //linearCommentaire.setBackgroundResource(R.drawable.back_comm_tra);
                    //linearCommentaire.addView(d2,layoutParamsTextCOmmentaire);
                    //linear.addView(linearCommentaire);


                    linear.addView(d2,layoutParamsTextCOmmentaire);

                    /**/

                    ImageView imgLine = new ImageView(DetailParcJardin.this);
                    imgLine.setImageDrawable(getResources().getDrawable(R.drawable.line_2_min));
                    //imgLine.setLayoutParams(new FrameLayout.LayoutParams(100,100));

                    //imgLine.setScaleType(ImageView.ScaleType.FIT_START);
                    linear.addView(imgLine);
                    /**/
                    //ImageView imgLigne = new ImageView(DetailParcJardin.this);
                    //imgLigne.setImageDrawable(getResources().getDrawable(R.drawable.ligne));
                    //imgLigne.setLayoutParams(new FrameLayout.LayoutParams(0,500));

                    /*img.setScaleX(0.5f);
                    img.setScaleY(0.5f);
                    img.setLeft(1);
                    img.setRight(1);*/
                    //img.setScaleType(ImageView.ScaleType.FIT_START);
                    //linear.addView(imgLigne);

                    linearH.addView(linear);
                    ll2.addView(linearH);



                //description.setText("---------hello world--------");
                //ll.addView(description); //Autre ligne problèmatique

                }

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
