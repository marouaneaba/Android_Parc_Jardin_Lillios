package com.example.abk.parcjardin.Services;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abk.parcjardin.R;

/**
 * Created by abakarim on 17/01/18.
 */

public class CommentaireFragment extends DialogFragment {

    @Override
    public void onPause() {
        super.onPause();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.ajout_commentaire,null);

        setCancelable(true  );
        return view;
    }
}
