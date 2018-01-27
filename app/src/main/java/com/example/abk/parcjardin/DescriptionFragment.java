package com.example.abk.parcjardin;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by abk on 27/01/2018.
 */

public class DescriptionFragment extends DialogFragment {

    private TextView descritpion;
    private String descriptionS;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.description_fragment,null);
        descritpion = (TextView)view.findViewById(R.id.descriptionText);

        descritpion.setText(descriptionS.toString());
        setCancelable(true);
        return view;
    }

    public void setDescription(String description){
        this.descriptionS = description;
    }


}
