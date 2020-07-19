package com.example.controlbogie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;

public class MenuActivity extends AppCompatActivity {
    private Button bCerrarSesion;
    private ToggleButton bApagarEncender;
    private Button bBaseDeDatos;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        bCerrarSesion = (Button) findViewById(R.id.cerrarSesion);
        bApagarEncender = (ToggleButton) findViewById(R.id.apagarEncender);
        bBaseDeDatos = (Button) findViewById(R.id.datosAlmacenados);

        bCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivities(new Intent[]{new Intent( MenuActivity.this, MainActivity.class)});
                finish();
            }
        });
    }
}