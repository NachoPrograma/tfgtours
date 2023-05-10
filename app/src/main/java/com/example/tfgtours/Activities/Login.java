package com.example.tfgtours.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgtours.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText edt_login_email;
    private EditText edt_login_clave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_clave = (EditText) findViewById(R.id.edt_login_clave);

        mAuth = FirebaseAuth.getInstance();
        // PRUEBAS EN FIREBASE REALTIME
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();
    }

    public void loguearAlumno(View view) {
        String email = String.valueOf(edt_login_email.getText());
        String password = String.valueOf(edt_login_clave.getText());
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "Logueado correctamente");
                            Toast.makeText(Login.this, "Logueado correctamente", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();


                           Intent intent = new Intent(Login.this, ListaCiudades.class);

                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "error al loguearse", task.getException());
                            Toast.makeText(Login.this, "Error al loguearse, sin no tiene cuenta debe registrarse", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

    public void registrar(View view) {
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }
}