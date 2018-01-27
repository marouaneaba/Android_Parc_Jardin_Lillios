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

    @Override
    public void onPause() {
        super.onPause();
        //getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.dialog_fragment,null);
        ImageView mImageView = (ImageView)view.findViewById(R.id.imageF);
        Picasso.with(getContext()).load(message).into(mImageView);
        setCancelable(true);
        return view;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
