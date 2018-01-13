package com.example.abk.parcjardin;

import com.example.abk.parcjardin.models.ParcJardin;
import com.google.android.gms.maps.GoogleMap;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by abk on 08/01/2018.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class PlacesDisplayTask extends AsyncTask<Object, Integer, GoogleMap > {



    List<ParcJardin> ParcJardins;
    GoogleMap googleMap;

    @Override
    protected GoogleMap doInBackground(Object... inputObj) {

        List<HashMap<String, String>> googlePlacesList = null;
        Places placeJsonParser = new Places();

        try {
            googleMap = (GoogleMap) inputObj[0];
            ParcJardins = (List<ParcJardin>) inputObj[1];

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        }
        return googleMap;
    }

    @Override
    protected void onPostExecute(GoogleMap map) {
        map.clear();

        for(int i=0;i<ParcJardins.size();i++){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(ParcJardins.get(i).getL(),ParcJardins.get(i).getG()));
            map.addMarker(markerOptions);
        }
        /*
        googleMap.clear();
        List<LatLng> LatLngs = new ArrayList<>();
        LatLngs.add(new LatLng(50.611881,3.141374) );
        LatLngs.add(new LatLng(50.613007,3.138083) );
        LatLngs.add(new LatLng(50.612278,3.140411) );
        LatLngs.add(new LatLng(50.612060,3.140164) );
        LatLngs.add(new LatLng(50.612156,3.140057) );
        LatLngs.add(new LatLng(50.612054,3.139113) );
        LatLngs.add(new LatLng(50.612156,3.139263) );

        for (int i = 0; i < LatLngs.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(LatLngs.get(i));
            //markerOptions.title(placeName + " : " + vicinity);
            googleMap.addMarker(markerOptions);
        }*/
        /*for (int i = 0; i < list.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = list.get(i);
            double lat = Double.parseDouble(googlePlace.get("lat"));
            double lng = Double.parseDouble(googlePlace.get("lng"));
            String placeName = googlePlace.get("place_name");
            String vicinity = googlePlace.get("vicinity");
            LatLng latLng = new LatLng(lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : " + vicinity);
            googleMap.addMarker(markerOptions);
        }*/
    }
}
