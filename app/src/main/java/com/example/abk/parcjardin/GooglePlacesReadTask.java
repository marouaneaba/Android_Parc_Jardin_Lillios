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
        //List<LatLng> LatLngs = new ArrayList<>();
        /*for(int i=0;i<parcJardinns.size();i++){
            LatLngs.add(new LatLng(parcJardinns.get(i).getL(),parcJardinns.get(i).getG()));
        }*/

        /*LatLngs.add(new LatLng(50.611881,3.141374) );
        LatLngs.add(new LatLng(50.613007,3.138083) );
        LatLngs.add(new LatLng(50.612278,3.140411) );
        LatLngs.add(new LatLng(50.612060,3.140164) );
        LatLngs.add(new LatLng(50.612156,3.140057) );
        LatLngs.add(new LatLng(50.612054,3.139113) );
        LatLngs.add(new LatLng(50.612156,3.139263) );
        */
        for (int i = 0; i < parcJardinns.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng mLatLng = new LatLng(parcJardinns.get(i).getL(),parcJardinns.get(i).getG());
            markerOptions.position(mLatLng);
            if(parcJardinns.get(i).getType().toUpperCase().equals("PARC")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_arbre_resize));
            }else if(parcJardinns.get(i).getType().toUpperCase().equals("JARDIN")){
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.parc_map_resize4));
            }

            //markerOptions.title(placeName + " : " + vicinity);
            map.addMarker(markerOptions);
        }
        /*
        PlacesDisplayTask placesDisplayTask = new PlacesDisplayTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = result;
        placesDisplayTask.execute(toPass);*/
    }
}
