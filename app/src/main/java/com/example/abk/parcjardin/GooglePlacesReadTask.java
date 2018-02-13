package com.example.abk.parcjardin;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.abk.parcjardin.models.ParcJardin;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abk on 08/01/2018.
 */

public class GooglePlacesReadTask extends AsyncTask<Object, Integer, GoogleMap> {

    String googlePlacesData = null;
    GoogleMap googleMap;
    List<ParcJardin> parcJardinns;

    @Override
    protected GoogleMap doInBackground(Object... inputObj) {
        try {
            googleMap = (GoogleMap) inputObj[0];
            parcJardinns = (List<ParcJardin>) inputObj[1];

        } catch (Exception e) {
            Log.d("Google Place Read Task", e.toString());
        }
        return googleMap;
    }

    @Override
    protected void onPostExecute(GoogleMap map) {

        map.clear();


        if(parcJardinns != null)
        for (int i = 0; i < parcJardinns.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng mLatLng = new LatLng(Double.parseDouble(parcJardinns.get(i).getL()),Double.parseDouble(parcJardinns.get(i).getG()));


            markerOptions.position(mLatLng);
            markerOptions.title(parcJardinns.get(i).getName());
            if(parcJardinns.get(i).getType().toUpperCase().equals("PARC")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.parc_map_resize4));
            }else if(parcJardinns.get(i).getType().toUpperCase().equals("JARDIN")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_arbre_resize));
            }


            map.addMarker(markerOptions);
        }

    }
}
