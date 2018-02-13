package com.example.abk.parcjardin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by abk on 11/01/2018.
 */

public class DFragment extends DialogFragment  {


    private String message;
    private String nameParcJardin;
    private ImageView mImageView;

    @Override
    public void onPause() {
        super.onPause();
        //getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.dialog_fragment,null);
        mImageView = (ImageView)view.findViewById(R.id.imageF);
        String nameParcJardinRegex = nameParcJardin.replaceAll(" ","_");
        String Url = "https://obscure-reef-42267.herokuapp.com/images/"+nameParcJardinRegex+"/"+nameParcJardinRegex+(message)+".jpg";

        Picasso.with(getContext()).load(Url).into(mImageView);
        setCancelable(true);
        return view;
    }

    public void setMessage(String message){
        this.message = message;

    }

    public void setNameParcJardin(String nameParcJardin){
        this.nameParcJardin = nameParcJardin;
    }
}
