package com.example.tfgtours.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import com.example.tfgtours.Activities.Login;
import com.example.tfgtours.R;
import com.example.tfgtours.Clases.Ciudades;
import com.example.tfgtours.Recyclerview.CiudadViewHolder;
import com.example.tfgtours.Utilidades.ImagenesBlobBitmap;
import com.example.tfgtours.Utilidades.ImagenesFirebase;

public class DetallesCiudad extends AppCompatActivity {

    public static final String EXTRA_POSICION_DEVUELTA =  "es.joseljg.detallesciudadactivity.posicion";
    public static final String EXTRA_TIPO = "es.joseljg.detallesciudadactivity.tipo";
    EditText edt_detalles_nombre = null;
    EditText edt_detalles_descripcion = null;
    String id_antiguo ="";
    int posicion = -1;
    public static final int NUEVA_IMAGEN = 1;
    Uri imagen_seleccionada = null;
    ImageView img_detalles_foto_ciudad = null;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_ciudad);
        edt_detalles_nombre = (EditText) findViewById(R.id.edt_detalles_nombre);
        // edt_detalles_nombre.setEnabled(false);
        edt_detalles_descripcion = (EditText) findViewById(R.id.edt_detalles_descripcion);
        img_detalles_foto_ciudad = (ImageView) findViewById(R.id.img_detalles_foto_ciudad);
        //--------------------------------------------------------------------------
        Intent intent = getIntent();
        if(intent != null)
        {
            Ciudades p = (Ciudades)intent.getSerializableExtra(CiudadViewHolder.EXTRA_ALUMNO_ITEM);
            edt_detalles_nombre.setText(p.getNombre());
            edt_detalles_descripcion.setText(p.getDescripcion());
            id_antiguo = p.getNombre();
            //cargo la foto
            byte[] fotobinaria = (byte[]) intent.getByteArrayExtra(CiudadViewHolder.EXTRA_ALUMNO_IMAGEN);
            Bitmap fotobitmap = ImagenesBlobBitmap.bytes_to_bitmap(fotobinaria, 200,200);
            img_detalles_foto_ciudad.setImageBitmap(fotobitmap);
            // obtengo la posicion
            posicion = intent.getIntExtra(CiudadViewHolder.EXTRA_POSICION_CASILLA,-1);
        }

    }

    //--------------------------------------------------------------------------
    //--------CODIGO PARA CAMBIAR LA IMAGEN----------------
    public void cambiar_imagen(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una imagen");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, NUEVA_IMAGEN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NUEVA_IMAGEN && resultCode == Activity.RESULT_OK) {
            imagen_seleccionada = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagen_seleccionada);
                img_detalles_foto_ciudad.setImageBitmap(bitmap);

                //---------------------------------------------

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //-----------------------------------------------------------------------



    public void cambiar_imagen_detalles(View view) {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");

        Intent chooserIntent = Intent.createChooser(getIntent, "Selecciona una imagen");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent, NUEVA_IMAGEN);
    }


}