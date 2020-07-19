package com.example.controlbogie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MenuActivity extends AppCompatActivity {
    private Button bCerrarSesion;
    private ToggleButton bApagarEncender;
    private Button bBaseDeDatos;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference(); //Aqui nos referimos al nodo principal de nuestra base de datos

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



        bApagarEncender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(true){
                    Map<String, Object> map = new HashMap<>();
                    map.put("test", b);
                    mDatabase.child("raspi").updateChildren(map);
                    bApagarEncender.setBackgroundColor(Color.RED);
                }    else{
                      Map<String, Object> map = new HashMap<>();
                      map.put("test", b);
                      mDatabase.child("raspi").updateChildren(map);

                     }
            }

        });
    }
}