package com.example.abk.parcjardin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by abk on 10/01/2018.
 */

public class DetailParcJardin extends AppCompatActivity {


    private TextView text1;
    private TextView text2;
    private Double latitude;
    private Double longitude;
    private String URL ="https://www.google.com/maps/search/?api=1";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailparcjardin);

        text1 = (TextView)findViewById(R.id.latitude);
        text2 = (TextView)findViewById(R.id.logitude);

        Intent intent = getIntent();
        latitude = Double.parseDouble(intent.getStringExtra("latitude"));
        longitude = Double.parseDouble(intent.getStringExtra("longitude"));

        text1.setText(latitude.toString());
        text2.setText(longitude.toString());
    }


    public void iteneraire(View v){
        Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(URL+"&query="+this.latitude+","+this.longitude));
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
