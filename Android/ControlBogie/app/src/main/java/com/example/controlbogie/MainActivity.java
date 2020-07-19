package com.example.controlbogie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button  bIniciar;
    private Button bRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bIniciar = (Button) findViewById(R.id.botoniniciar);
        bRegistrar = (Button) findViewById(R.id.botonregistrar);

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent( MainActivity.this, MenuActivity.class)});
            }
        });

        bRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent( MainActivity.this, RegActivity.class)});
            }
        });


    }
}