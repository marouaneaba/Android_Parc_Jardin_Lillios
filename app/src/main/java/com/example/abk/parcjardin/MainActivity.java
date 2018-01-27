package com.example.abk.parcjardin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.abk.parcjardin.Services.Service;
import com.example.abk.parcjardin.models.Categorie;
import com.example.abk.parcjardin.models.Commentaire;
import com.example.abk.parcjardin.models.ParcJardin;
import com.example.abk.parcjardin.models.Repo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends FragmentActivity implements LocationListener {

    private static final String GOOGLE_API_KEY = "AIzaSSDFSDF8Kv2eP0PM8adf5dSDFysdfas323SD3HA";
    GoogleMap googleMap;
    EditText placeText;
    double latitude = 0;
    double longitude = 0;
    private int PROXIMITY_RADIUS = 5000;
    private SearchView mSearchView;
    private List<ParcJardin> parcJardinP = new ArrayList<>();


    public Service URLretrofit(){
        Service service = new RestAdapter.Builder()
                .setEndpoint(Service.ENDPOINT)
                .build()
                .create(Service.class);
        return service;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }
        setContentView(R.layout.activity_main);



        placeText = (EditText) findViewById(R.id.placeText);
        //Button btnFind = (Button) findViewById(R.id.btnFind);
        mSearchView = (SearchView) findViewById(R.id.searchV);

        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMap);
        googleMap = fragment.getMap();
        googleMap.setMyLocationEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String bestProvider = LocationManager.GPS_PROVIDER;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 2);
            }
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
            }
            return;
        }

        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 20, 0, this);



        /*btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = placeText.getText().toString();
                StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                googlePlacesUrl.append("location=" + latitude + "," + longitude);
                googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
                googlePlacesUrl.append("&types=" + type);
                googlePlacesUrl.append("&sensor=true");
                googlePlacesUrl.append("&key=" + GOOGLE_API_KEY);

                GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
                Object[] toPass = new Object[2];
                toPass[0] = googleMap;
                toPass[1] = googlePlacesUrl.toString();
                googlePlacesReadTask.execute(toPass);
            }
        });*/

        /*GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
        Object[] toPass = new Object[1];
        toPass[0] = googleMap;
        googlePlacesReadTask.execute(toPass);*/
        getParcJardinByService("Tout");
        //getAllParcJardin();
        /*if(this.parcJardinP != null)
            Toast.makeText(getApplication(),"size marouane: "+this.parcJardinP.size(),Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplication(),"NULL ",Toast.LENGTH_SHORT).show();*/

        //addPointMap(ParcJardinns);

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Toast.makeText(getApplication(),"marker longitude : "+marker.getPosition().longitude,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailParcJardin.class);
                intent.putExtra("latitude", String.valueOf(marker.getPosition().latitude));
                intent.putExtra("longitude", String.valueOf(marker.getPosition().longitude));
                startActivity(intent);
                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Service service = URLretrofit();
                Toast.makeText(getApplication(),"Vous chercher m : "+query,Toast.LENGTH_SHORT).show();
                service.getParcJardinnSearch(query, new Callback<List<ParcJardin>>() {
                    @Override
                    public void success(List<ParcJardin> parcJardins, Response response) {
                        if(parcJardins == null || parcJardins.size() == 0 ){
                            Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
                        }else{
                            addPointMap(parcJardins);
                        }
                        Toast.makeText(getApplication(),"Parc Search : "+parcJardins.get(0).getName(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //getCategorie();
        //getCommeantaire();
        //sendPOST();

    }

    /*public void search(View v){
        String search = placeText.getText().toString();
        Service service = URLretrofit();
        service.getParcJardinnSearch("search",new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> ParcJardins, Response response) {
                if(ParcJardins == null || ParcJardins.size() == 0 ){
                    Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
                }else{
                    addPointMap(ParcJardins);
                }
                Toast.makeText(getApplication(),"Parc Search : "+ParcJardins.get(0).getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    public void test(View v){
        Intent intent = new Intent(MainActivity.this, DetailParcJardin.class);
        intent.putExtra("latitude", "1.2");
        intent.putExtra("longitude", "30.6");
        startActivity(intent);
    }
    public void addPointMap(List<ParcJardin> parcJardins){

        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = parcJardins;
        googlePlacesReadTask.execute(toPass);
    }



    public void getParcJardinByService(final String serviceName){

        Service service = URLretrofit();
        service.getParcJardinnService(serviceName, new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                Toast.makeText(getApplication(),"parcJardin Détail : "+parcJardins,Toast.LENGTH_SHORT).show();

                addPointMap(parcJardins);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public List<ParcJardin> getParcJardin(){
        return this.parcJardinP;
    }

    public void getParcJardinByLatitudeLongitude(double latitude,double longitude){

        Service service = URLretrofit();
        service.getParcJardinLatitudeLongitude(latitude,longitude, new Callback<ParcJardin>() {

            @Override
            public void success(ParcJardin parcJardin, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    protected void getCategorie(){
        Service service = URLretrofit();
        service.getCategorie(new Callback<List<Categorie>>() {
            @Override
            public void success(List<Categorie> Categories, Response response) {
                Toast.makeText(getApplication(),"Succeus : ",Toast.LENGTH_SHORT).show();
                afficherCategorie(Categories);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Failure : "+error,Toast.LENGTH_SHORT).show();
                System.out.println("***********erreor: "+error);
            }
        });
    }

    public void afficherCategorie(List<Categorie> Categories){
        System.out.println("***********categorie : "+Categories);
        for(int i=0;i<Categories.size();i++){
            Toast.makeText(this,"categorie : "+Categories.get(i),Toast.LENGTH_SHORT).show();
        }
    }

    public void getAllParcJardin(){
        Service service = URLretrofit();
        service.getParcJardinn(new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                setParcJardin(parcJardins);
                addPointMap(parcJardins);
                //MainActivity.this.parcJardinP = parcJardins;
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllParc(){
        Service service = URLretrofit();
        service.getAllParc(new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                addPointMap(parcJardins);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Error récupération Parc !!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAllJardin(){
        Service service = URLretrofit();
        service.getAllJardin(new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                addPointMap(parcJardins);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Error récupération Jardin !!",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setParcJardin(List<ParcJardin> parcJardinns){
        //Toast.makeText(getApplication(),"size leee :  "+parcJardinns,Toast.LENGTH_SHORT).show();
        for(int i=0;i<parcJardinns.size();i++){
            this.parcJardinP.add(parcJardinns.get(i));
            Toast.makeText(getApplication(),"size leee :  "+parcJardinns.size(),Toast.LENGTH_SHORT).show();
        }
    }



/*
    protected void getCommeantaire(){
        Toast.makeText(this,"1 : ",Toast.LENGTH_SHORT).show();
        Service service = URLretrofit();
        Toast.makeText(this,"2 : ",Toast.LENGTH_SHORT).show();
        service.getCommentaire(new Callback<List<Commentaire>>() {
            @Override
            public void success(List<Commentaire> commentaires, Response response) {
                Toast.makeText(getApplication(),"3 : ",Toast.LENGTH_SHORT).show();
                afficherCommeantaire(commentaires);
                Toast.makeText(getApplication(),"4 : ",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"5 : ",Toast.LENGTH_SHORT).show();
                System.out.println("erreur : "+error);
                Toast.makeText(getApplication(),"6 : ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void sendPOST(){
        Service service = URLretrofit();
        service.postCommentaire("android description",false,new Callback<List<Repo>>() {

            @Override
            public void success(List<Repo> repos, Response response) {
                System.out.println("envoyer");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("erreur");
            }
        });
    }

    public void afficherCommeantaire(List<Commentaire> commentaires) {
        for(int i=0;i<commentaires.size();i++){
            Toast.makeText(this,"commeantaire : "+commentaires.get(i),Toast.LENGTH_SHORT).show();
        }

    }*/

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    public void Parc(View v){
        Toast.makeText(getApplication(),"Tous les parcs",Toast.LENGTH_SHORT).show();
        //getAllParc();
        getParcJardinByService("Parc");
    }

    public void Jardin(View v){
        Toast.makeText(getApplication(),"Tous les jardins",Toast.LENGTH_SHORT).show();
        //getAllJardin();
        getParcJardinByService("Jardin");
    }

    public void Sport(View v){
        Toast.makeText(getApplication(),"Sport",Toast.LENGTH_SHORT).show();
        getParcJardinByService("FOOTING");
    }

    public void Etude(View v){
        Toast.makeText(getApplication(),"Etude",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Etude");
    }

    public void restauration(View v){
        Toast.makeText(getApplication(),"Restauration",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Restauration");
    }

    public void promoner(View v){
        Toast.makeText(getApplication(),"Promoner",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Promoner");
    }

    public void Ecouter(View v){
        Toast.makeText(getApplication(),"Ecouter",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Ecouter");
    }

    public void observer(View v){
        Toast.makeText(getApplication(),"Observer",Toast.LENGTH_SHORT).show();
        getParcJardinByService("Observer");
    }

    public void Tout(View v){
        Toast.makeText(getApplication(),"Tout parcs & jardins",Toast.LENGTH_SHORT).show();
        //getAllParcJardin();
        getParcJardinByService("Tout");
    }

}
