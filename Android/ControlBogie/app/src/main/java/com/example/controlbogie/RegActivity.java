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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity {

    private Button  bVolver;
    private Button bAceptar;
    private EditText tUsuario;
    private EditText tPass;
    private EditText tEmail;
    //Variable de los Datos del Usuario a Registrar
    private String usr = "";
    private String pass = "";
    private String mail = "";

    FirebaseAuth mAuth;
    DatabaseReference dDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        mAuth = FirebaseAuth.getInstance();
        dDatabaseRef = FirebaseDatabase.getInstance().getReference(); //Aqui nos referimos al nodo principal de nuestra base de datos

        tUsuario = (EditText) findViewById(R.id.usuario);
        tEmail = (EditText) findViewById(R.id.emailreg);
        tPass = (EditText) findViewById(R.id.contrareg);
        bAceptar = (Button) findViewById(R.id.botonAceptar);
        bVolver = (Button) findViewById(R.id.botonVolver);

        bAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usr = tUsuario.getText().toString();
                pass = tPass.getText().toString();
                mail = tEmail.getText().toString();
                //Necesitamos saber si se ingreso informacion en las cajas de texto
                if(!usr.isEmpty() && !pass.isEmpty() && !mail.isEmpty()){
                    //La contrasena debe ser de al menos 6 caracteres
                    if (pass.length() >= 6){
                        registerUser(); //Llamamos al metodo registrar Usuario
                    }else{ //Le indicamos al usuario su error
                        Toast.makeText(RegActivity.this, "La Password debe tener al menos 6 caracteres.", Toast.LENGTH_SHORT).show();
                    }
                }else{ //Le indicamos al usuario que debe llenar los campos
                    Toast.makeText(RegActivity.this, "Porfavor, complete los campos y vuelva a intentarlo.", Toast.LENGTH_SHORT).show();

                }
            }

            private void registerUser() {
                //Registramos al usuario en nuestra Firebase
                mAuth.createUserWithEmailAndPassword(mail, usr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("Usuario", usr);
                            map.put("E-Mail", mail);
                            map.put("Usuario", pass);
                            String id = mAuth.getCurrentUser().getUid();
                            dDatabaseRef.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task2) {
                                    if (task2.isSuccessful()){ //Si los guardo correctamente en la base de datos vamos a la pantalla menu
                                        startActivities(new Intent[]{new Intent( RegActivity.this,  MenuActivity.class)});
                                        finish();
                                    }else{
                                        Toast.makeText(RegActivity.this, "Error.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(RegActivity.this, "No se pudo guardar en la base de datos.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });




        bVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivities(new Intent[]{new Intent( RegActivity.this,  MainActivity.class)});
                finish();
            }
        });
    }


}