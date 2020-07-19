package com.example.controlbogie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegActivity extends AppCompatActivity {

    private Button  bVolver;
    private Button bAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        bAceptar = (Button) findViewById(R.id.botonAceptar);
        bVolver = (Button) findViewById(R.id.botonVolver);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent( RegActivity.this, MenuActivity.class)});
            }
        });

        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent( RegActivity.this,  MainActivity.class)});
            }
        });
    }


}