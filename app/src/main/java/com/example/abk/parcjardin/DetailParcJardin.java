package com.example.abk.parcjardin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abk.parcjardin.Services.Service;
import com.example.abk.parcjardin.models.Categorie;
import com.example.abk.parcjardin.models.Commentaire;
import com.example.abk.parcjardin.models.ParcJardin;
import com.squareup.picasso.Picasso;

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


    private TextView text1,plus;
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


        plus = (TextView)findViewById(R.id.plus);
        String htmlString = "<u>Plus</u>";
        plus.setText(Html.fromHtml(htmlString));
        Name = (TextView)findViewById(R.id.Name);
        Horaire = (TextView)findViewById(R.id.Horaire);
        Addresse = (TextView)findViewById(R.id.Addresse);
        Description = (TextView)findViewById(R.id.Description);

        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));


        //getParcJardinByLatitudeLongitude(latitude,longitude);
        Actualiser(latitude,longitude);
        //getCommentaireJardinParc("NameParcJardin");
        //getImage();
        //getCategorie();

    }
        // afficher le nom/addresse/Horaire d'un parc/jardin , et récupper par des web service les catégorie associé a se parc/jardin
    public void Actualiser(double latitude,double longitude){
        Service service = URLretrofit();
        service.getParcJardinLatitudeLongitude(latitude,longitude, new Callback<ParcJardin>() {
            @Override
            public void success(ParcJardin parcJardin, Response response) {
                Name.setText(parcJardin.getName());
                //Horaire.setText(parcJardin.getHoraires().toString());
                Addresse.setText(parcJardin.getAdresse());
                Description.setText(parcJardin.getDescription());
                setCategorie("parc");
                setImageParcJardin("parc");
                getCommentaireJardinParc("parc");
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Error get Détail Parc !!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //apré la récupération des catégorie d'un parc/jardin , appel la méthode la production dynamique des image à afficher à partir les catégorie récupérer
    public void setCategorie(String nameParcJardin){

        Service service = URLretrofit();
        service.getCategorieByParcJardinn(nameParcJardin, new Callback<List<Categorie>>() {
            @Override
            public void success(List<Categorie> categories, Response response) {
                imgCategorie(categories);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Error get Catégorie d'un parc/Jardin !!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void imgCategorie(List<Categorie> categories){
        /*List<String> categories = new ArrayList<String>();
        categories.add("sport");
        categories.add("etude");
        categories.add("restauration");*/


        LinearLayout ll2 = (LinearLayout) findViewById(R.id.imgCategories);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 20, 10, 5);
        //layoutParams.weight=80;
        layoutParams.width = 80;
        layoutParams.height=80;
        //ll.setOrientation(LinearLayout.VERTICAL);
        ll2.removeAllViews(); //Ligne problèmatique
        ll2.removeAllViewsInLayout();
        Toast.makeText(getApplication(),"size categorie : "+categories.size(),Toast.LENGTH_SHORT).show();
        for(int i=0;i<categories.size();i++){
            ImageView img = new ImageView(DetailParcJardin.this);

            if(categories.get(i).getNomcategorie().toUpperCase().equals("SPORT")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.footing));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("ETUDE")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.etude2));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("RESTAURATION")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.restauration));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("PARC")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.parc));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("JARDIN")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.jardin));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("PROMONER")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.promoner));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("ECOUTER")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.music));
            }
            if(categories.get(i).getNomcategorie().toUpperCase().equals("OBSERVER")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.observer));
            }

            /*img.setMaxWidth(1);
            img.layout(1,1,1,1);

            img.setLayoutParams(new FrameLayout.LayoutParams(100,100));*/
            //img.setScaleType(ImageView.ScaleType.FIT_START);
            ll2.addView(img,layoutParams);
        }
    }

    public void setImageParcJardin(String nameParcJardin){

        Service service = URLretrofit();
        service.getImagesParcJardin(nameParcJardin, new Callback<List<String>>() {
            @Override
            public void success(List<String> imagesURLName, Response response) {
                getImage(imagesURLName);
            }

            @Override
            public void failure(RetrofitError error) {
                getImage(new ArrayList<String>());
                Toast.makeText(getApplication(),"Error get List Image Parc/Jardin !!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getImage(List<String> imagesURLName){

        LinearLayout ll2 = (LinearLayout) findViewById(R.id.imgLinear);
        //LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //layoutParams.setMargins(10, 20, 10, 50);
        //ll.setOrientation(LinearLayout.VERTICAL);
        ll2.removeAllViews(); //Ligne problèmatique
        ll2.removeAllViewsInLayout();


        //for(int i=0;i<imagesURLName.size();i++){
        for(int i=0;i<4;i++){
            ImageView img = new ImageView(DetailParcJardin.this);
            /*Bitmap thumbImg;
            Toast.makeText(getApplication(),"Arrivé :4: ...  !!!",Toast.LENGTH_SHORT).show();
            try {
                //URL url = new URL(Service.ENDPOINT+"/image/"+imagesURLName.get(i));
                Toast.makeText(getApplication(),"Arrivé :5: ...  !!!",Toast.LENGTH_SHORT).show();
                URL url = new URL("https://www.salford.ac.uk/__data/assets/image/0008/890072/varieties/lightbox.jpg");
                Toast.makeText(getApplication(),"Arrivé :6: ...  !!!",Toast.LENGTH_SHORT).show();
                URLConnection thumbConn = url.openConnection();
                Toast.makeText(getApplication(),"Arrivé :7: ...  !!!",Toast.LENGTH_SHORT).show();
                //thumbConn.connect();
                Toast.makeText(getApplication(),"Arrivé :8: ...  !!!",Toast.LENGTH_SHORT).show();
                InputStream thumbIn = thumbConn.getInputStream();
                Toast.makeText(getApplication(),"Arrivé :9: ...  !!!",Toast.LENGTH_SHORT).show();
                BufferedInputStream thumbBuff = new BufferedInputStream(thumbIn);
                thumbImg = BitmapFactory.decodeStream(thumbBuff);
                thumbBuff.close();
                thumbIn.close();

                //Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                img.setImageBitmap(thumbImg);
            }catch(Exception r){

            }*/

            Picasso.with(getBaseContext()).load("https://www.salford.ac.uk/__data/assets/image/0008/890072/varieties/lightbox.jpg").into(img);

            //img.setImageDrawable(getResources().getDrawable(R.drawable.man2));
            img.setLayoutParams(new FrameLayout.LayoutParams(500,350));
            img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    DFragment dfFragment = new DFragment();
                    dfFragment.setMessage("https://www.salford.ac.uk/__data/assets/image/0008/890072/varieties/lightbox.jpg");
                    dfFragment.show(fm,"Big Image");
                    return false;
                }
            });
            //img.setPadding(2,2,2,2);
            //img.setMinimumWidth(50);
            //img.setMaxWidth(50);
            //img.setScaleType(ImageView.ScaleType.FIT_START);
            ll2.addView(img);
            ImageView imgLine = new ImageView(DetailParcJardin.this);
            imgLine.setImageDrawable(getResources().getDrawable(R.drawable.line2h));
            ll2.addView(imgLine);
        }


    }

    public void getCommentaireJardinParc(String NameParcJardin){

        Service service = URLretrofit();
        service.getCommenatiresByParcJardin(NameParcJardin, new Callback<List<Commentaire>>() {
            @Override
            public void success(List<Commentaire> commentaires, Response response) {

                Toast.makeText(getApplication(),"OUI COMMENTAIRE ARRIVE !!",Toast.LENGTH_SHORT).show();

                description = new TextView( DetailParcJardin.this);
                LinearLayout ll2 = (LinearLayout) findViewById(R.id.liner);
                ll2.removeAllViews(); //Ligne problèmatique
                ll2.removeAllViewsInLayout();

                for(int i=0;i<commentaires.size();i++){


                    LinearLayout linearH = new LinearLayout(DetailParcJardin.this);
                    linearH.setOrientation(LinearLayout.HORIZONTAL);

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
                    d.setText(commentaires.get(i).getName());
                    d.setRight(600);
                    linear.addView(d,layoutParams2);

                    RatingBar rating = new RatingBar(DetailParcJardin.this);
                    rating.setScaleX(0.5f);
                    rating.setScaleY(0.5f);
                    rating.setRating(commentaires.get(i).getNbrEtoile());//.setNumStars(2);
                    rating.setEnabled(false);
                    LinearLayout.LayoutParams layoutParamsRating = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParamsRating.setMarginStart(85);
                    linear.addView(rating,layoutParamsRating);
                    TextView d2 = new TextView( DetailParcJardin.this);
                    d2.setText(commentaires.get(i).getCommentaire());

                    d2.setPadding(40,0,1,0);
                    //LinearLayout.LayoutParams layoutParamsTextCOmmentaire = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    //layoutParamsTextCOmmentaire.setMargins(0,0,0,0);
                    //l,t,r,b

                    //linear.addView(d2,layoutParamsTextCOmmentaire);
                    linear.addView(d2);
                    ImageView imgLine = new ImageView(DetailParcJardin.this);
                    imgLine.setImageDrawable(getResources().getDrawable(R.drawable.line_2_min));
                    linear.addView(imgLine);


                    linearH.addView(linear);
                    ll2.addView(linearH);
                    Toast.makeText(getApplication(),"comemntaire :11 "+commentaires.get(i),Toast.LENGTH_SHORT).show();
                }
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
                    rating.setScaleY(0.5f);
                    rating.setNumStars(5);
                    rating .setMax(5);
                    rating.setEnabled(true);
                    rating.setMinimumHeight(5);
                    //rating.setPadding(1,0,1,0);//.layout(1,1,1,1);
                    rating.setRating(3.5f);
                    LinearLayout.LayoutParams layoutParamsRating = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParamsRating.setMarginStart(85);
                    linear.addView(rating,layoutParamsRating);

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

                    //d2.setPadding(0,0,0,0);

                    //d2.setPadding(1,0,0,0);
                    LinearLayout.LayoutParams layoutParamsTextCOmmentaire = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    //layoutParamsTextCOmmentaire.setMargins(0,0,0,0);
                    //l,t,r,b
                    //linearCommentaire.setBackgroundResource(R.drawable.back_comm_tra);
                    //linearCommentaire.addView(d2,layoutParamsTextCOmmentaire);
                    //linear.addView(linearCommentaire);


                    linear.addView(d2,layoutParamsTextCOmmentaire);
                    //linear.addView(d2);
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


    public void Plus(View v){
        DescriptionFragment des = new DescriptionFragment();
        des.setDescription(Description.getText().toString());
        des.show(fm,"description : ");
    }

    public void iteneraire(View v){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(URL+"&query="+this.latitude+","+this.longitude));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }


}
