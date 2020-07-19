package com.example.controlbogie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button  bIniciar;
    private Button bRegistrar;
    private EditText tEmail;
    private EditText tPass;
    private String email = "";
    private String pass = "";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        tEmail = (EditText) findViewById(R.id.email);
        tPass = (EditText) findViewById(R.id.password);
        bIniciar = (Button) findViewById(R.id.botoniniciar);
        bRegistrar = (Button) findViewById(R.id.botonregistrar);

        bIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = tEmail.getText().toString();
                pass = tPass.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    loginUser();
                }else{
                    Toast.makeText(MainActivity.this, "Porfavor, complete los campos y vuelva a intentarlo.", Toast.LENGTH_SHORT).show();
                }
            }

            private void loginUser() {
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivities(new Intent[]{new Intent( MainActivity.this, MenuActivity.class)});
                        }else{
                            Toast.makeText(MainActivity.this, "No se pudo iniciar sesion.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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