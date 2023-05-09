package com.example.tfgtours;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registrar extends AppCompatActivity {
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private EditText edt_login_email;
    private EditText edt_login_clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }


    public void registrarUsuario(View view) {
        String email = String.valueOf(edt_login_email.getText()).trim();
        String password = String.valueOf(edt_login_clave.getText());
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebase1", "usuario registrado correctamente");
                            Toast.makeText(Registrar.this, "usuario registrado correctamente", Toast.LENGTH_SHORT).show();
//                            // updateUI(user);
                            Intent intent = new Intent(Registrar.this, Login.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebase1", "no se pudo registrar al usuario", task.getException());
                            Toast.makeText(Registrar.this, "no se pudo registrar al usuario", Toast.LENGTH_SHORT).show();
                            //  updateUI(null);
                        }
                    }
                });

    }
}