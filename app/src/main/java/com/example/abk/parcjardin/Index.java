package com.example.abk.parcjardin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class Index extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
    }

    public void Go(View v){
        Intent intent = new Intent(Index.this, MainActivity.class);
        startActivity(intent);
    }

}
