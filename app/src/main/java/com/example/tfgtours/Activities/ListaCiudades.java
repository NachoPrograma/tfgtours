package com.example.tfgtours.Activities;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tfgtours.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.util.ArrayList;
import com.example.tfgtours.Clases.Ciudades;
import com.example.tfgtours.Recyclerview.ListaCiudadAdapter;

public class ListaCiudades extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        else{
            Toast.makeText(this, "debes autenticarte primero", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    //---------------------------------------------------------------------
    private RecyclerView rv_ciudad = null;
    private ListaCiudadAdapter adaptadorCiudad = null;
    private DatabaseReference myRefciudad = null;
    private DatabaseReference myRefciudad1 = null;
    private ArrayList<Ciudades> ciudad;
    private EditText edt_buscar_nombre1;
    public static int PETICION1 = 1;
    //-----------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ciudades);
        rv_ciudad = (RecyclerView) findViewById(R.id.rv_ciudad);
        edt_buscar_nombre1 = (EditText) findViewById(R.id.edt_buscar_nombre2);
        //-------------------------------------------------------------
        mAuth = FirebaseAuth.getInstance();
        ciudad = new ArrayList<Ciudades>();
        //-----------------------------------------------------------
        adaptadorCiudad = new ListaCiudadAdapter(this,ciudad);
        rv_ciudad.setAdapter(adaptadorCiudad);
        myRefciudad = FirebaseDatabase.getInstance().getReference("ciudadhashmap");
        myRefciudad.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                adaptadorCiudad.getCiudades().clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Ciudades a = (Ciudades) dataSnapshot.getValue(Ciudades.class);
                    ciudad.add(a);
                    adaptadorCiudad.setCiudades(ciudad);
                    adaptadorCiudad.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "firebase1", String.valueOf(error.toException()));
            }
        });

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            rv_ciudad.setLayoutManager(new GridLayoutManager(this,2));
        } else {
            // In portrait
            rv_ciudad.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public void buscarCiudad1(View view) {

        String texto = String.valueOf(edt_buscar_nombre1.getText());
        myRefciudad1 = FirebaseDatabase.getInstance().getReference("ciudadhashmap");
        myRefciudad1.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<String> keys1 = new ArrayList<String>();
                ArrayList<Ciudades> ciudad1 = new ArrayList<Ciudades>();
                for (DataSnapshot keynode : snapshot.getChildren()) {
                    keys1.add(keynode.getKey());
                    Ciudades a = keynode.getValue(Ciudades.class);
                    if(a.getNombre().contains(texto)) {
                        ciudad1.add(keynode.getValue(Ciudades.class));
                    }
                }
                adaptadorCiudad.setCiudades(ciudad1);
                adaptadorCiudad.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.i( "firebase1", String.valueOf(error.toException()));
            }
        });

    }
    //---------------------------------------------------------------------------------
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1 && resultCode == Activity.RESULT_OK) {
            int posicion = data.getIntExtra(DetallesCiudad.EXTRA_POSICION_DEVUELTA,-1);
            String tipo = data.getStringExtra(DetallesCiudad.EXTRA_TIPO);
            if(tipo.equalsIgnoreCase("edicion"))
            {
                adaptadorCiudad.notifyItemChanged(posicion);
                adaptadorCiudad.notifyDataSetChanged();
            }
            else if(tipo.equalsIgnoreCase("borrado"))
            {
                adaptadorCiudad.notifyItemRemoved(posicion);
                adaptadorCiudad.notifyDataSetChanged();
            }
            else{
                adaptadorCiudad.notifyDataSetChanged();
            }


            // this.recreate();
            //  getWindow().getDecorView().findViewById(android.R.id.content).invalidate();
        }
    }
    //------------------------------------------------------------------------------


}