package com.example.controlbogie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.HttpCookie;

public class MenuActivity extends AppCompatActivity {
    private Button bCerrarSesion;
    private ToggleButton bApagarEncender;
    private Button bBaseDeDatos;
    private FirebaseAuth mAuth;
    FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    DatabaseReference refBogie = mDatabase.getReference("Bogie");
    DatabaseReference refEnciende;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        refEnciende = refBogie.child("Enciende");

        bCerrarSesion = (Button) findViewById(R.id.cerrarSesion);
        bApagarEncender = (ToggleButton) findViewById(R.id.apagarEncender);
        bBaseDeDatos = (Button) findViewById(R.id.datosAlmacenados);

        bApagarEncender.setTextOn("APAGAR");
        bApagarEncender.setTextOn("ENCENDER");

        controlLED(refEnciende, bApagarEncender);

        bCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivities(new Intent[]{new Intent( MenuActivity.this, MainActivity.class)});
                finish();
            }
        });
    }

    private void controlLED(final DatabaseReference refData, final ToggleButton bApagarEncender) {
        bApagarEncender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                refData.setValue(isChecked);
            }
            });
        refData.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               Boolean estado = (boolean) snapshot.getValue();
               bApagarEncender.setChecked(estado);
               if(estado){
                   bApagarEncender.setTextOn("APAGAR");
               }else{
                   bApagarEncender.setTextOn("ENCENDER");
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}