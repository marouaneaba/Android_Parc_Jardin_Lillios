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
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.abk.parcjardin.Services.Service;
import com.example.abk.parcjardin.models.Categorie;
import com.example.abk.parcjardin.models.ParcJardin;
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

        mSearchView = (SearchView) findViewById(R.id.searchV);


        mSearchView.setIconifiedByDefault(false);
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




        getTousParcJardinLillios();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(MainActivity.this, DetailParcJardin.class);
                intent.putExtra("nameJardinParcLillios", marker.getTitle());
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
                            addPointMap(parcJardins);
                        }else{
                            addPointMap(parcJardins);
                        }
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

    }



    public void addPointMap(List<ParcJardin> parcJardins){

        GooglePlacesReadTask googlePlacesReadTask = new GooglePlacesReadTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = parcJardins;
        googlePlacesReadTask.execute(toPass);
    }

    public void getTousParcJardinLillios(){
        Service service = URLretrofit();
        service.getParcJardinn(new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {

                addPointMap(parcJardins);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplication(),"Parc Ou Jardin n'existe pas !! : "+error,Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getParcJardinByService(final String serviceName){

        Service service = URLretrofit();
        service.getParcJardinnService(serviceName, new Callback<List<ParcJardin>>() {
            @Override
            public void success(List<ParcJardin> parcJardins, Response response) {
                addPointMap(parcJardins);
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
        for(int i=0;i<parcJardinns.size();i++){
            this.parcJardinP.add(parcJardinns.get(i));
        }
    }





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
        //getParcJardinByService("Tout");
        getTousParcJardinLillios();
    }

}
