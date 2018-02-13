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
            markerOptions.position(new LatLng(Double.parseDouble(ParcJardins.get(i).getL()),Double.parseDouble(ParcJardins.get(i).getG())));
            map.addMarker(markerOptions);
        }

    }
}
