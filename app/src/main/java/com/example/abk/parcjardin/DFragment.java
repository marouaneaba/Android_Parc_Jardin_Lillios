package com.example.abk.parcjardin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.DialogFragment;
import android.view.ViewGroup;

/**
 * Created by abk on 11/01/2018.
 */

public class DFragment extends DialogFragment  {

    @Override
    public void onPause() {
        super.onPause();
        //getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.dialog_fragment,null);



        setCancelable(true  );
        return view;
    }
}
