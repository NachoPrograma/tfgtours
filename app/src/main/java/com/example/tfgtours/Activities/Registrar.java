package com.example.tfgtours.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgtours.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Registrar extends AppCompatActivity {
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText edt_login_email;
    private EditText edt_login_password;
    private EditText edt_login_nombre;
    private CheckBox edt_login_terminos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);
        edt_login_nombre = (EditText) findViewById(R.id.edt_login_nombre);
        edt_login_terminos = (CheckBox) findViewById(R.id.checkBox);
    }


    public void registrarUsuario(View view) {
        String email = String.valueOf(edt_login_email.getText()).trim();
        String password = String.valueOf(edt_login_password.getText());
        String nombre = String.valueOf(edt_login_nombre.getText());

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && email !=null && password !=null && nombre !=null) {
                            Log.i("firebase1", "Usuario registrado correctamente");
                            Toast.makeText(Registrar.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Registrar.this, Login.class);
                            startActivity(intent);
                        } else {

                            Log.i("firebase1", "No se pudo registrar al usuario", task.getException());
                            Toast.makeText(Registrar.this, "No se pudo registrar al usuario se deben de rellenar todos los campos", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}