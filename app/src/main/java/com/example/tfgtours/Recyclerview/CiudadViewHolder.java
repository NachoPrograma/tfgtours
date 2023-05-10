package com.example.tfgtours.Recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfgtours.Activities.DetallesCiudad;
import com.example.tfgtours.Activities.ListaCiudades;
import com.example.tfgtours.R;
import com.example.tfgtours.Clases.Ciudades;
import com.example.tfgtours.Utilidades.ImagenesBlobBitmap;

public class CiudadViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_ALUMNO_ITEM = "es.joseljg.ciudadviewholder.ciudad";
    public static final String EXTRA_ALUMNO_IMAGEN = "es.joseljg.ciudadviewholder.imagenciudad";
    public static final String EXTRA_POSICION_CASILLA = "es.joseljg.ciudadviewholder.posicion";
    // atributos
    private TextView txt_item_nombre;
    private TextView txt_item_curso;
    private ImageView img_item_ciudad;
    //-------------------------------------
    private ListaCiudadAdapter lpa;
    private Context contexto;

    public ImageView getImg_item_ciudad() {
        return img_item_ciudad;
    }

    public void setImg_item_ciudad(ImageView img_item_ciudad) {
        this.img_item_ciudad = img_item_ciudad;
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public CiudadViewHolder(@NonNull View itemView, ListaCiudadAdapter listaProductosAdapter) {
        super(itemView);
        txt_item_nombre = (TextView) itemView.findViewById(R.id.txt_item_nombre);
        img_item_ciudad = (ImageView) itemView.findViewById(R.id.img_item_ciudad);
        //-----------------------------------------------------------------------------
        lpa = listaProductosAdapter;
        itemView.setOnClickListener(this);
    }

    public TextView getTxt_item_nombre() {
        return txt_item_nombre;
    }

    public void setTxt_item_nombre(TextView txt_item_nombre) {
        this.txt_item_nombre = txt_item_nombre;
    }

    public TextView getTxt_item_curso() {
        return txt_item_curso;
    }

    public void setTxt_item_curso(TextView txt_item_curso) {
        this.txt_item_curso = txt_item_curso;
    }

    public ListaCiudadAdapter getLpa() {
        return lpa;
    }

    public void setLpa(ListaCiudadAdapter lpa) {
        this.lpa = lpa;
    }

    @Override
    public void onClick(View view) {
        int posicion = getLayoutPosition();
        Ciudades p = lpa.getCiudades().get(posicion);
        Intent intent = new Intent(lpa.getContexto(), DetallesCiudad.class);
        intent.putExtra(EXTRA_ALUMNO_ITEM,p);
        img_item_ciudad.buildDrawingCache();
        Bitmap foto_bm = img_item_ciudad.getDrawingCache();
        intent.putExtra(EXTRA_ALUMNO_IMAGEN, ImagenesBlobBitmap.bitmap_to_bytes_png(foto_bm));
        intent.putExtra(EXTRA_POSICION_CASILLA, posicion);
        //  lpa.getContexto().startActivity(intent);
        Context contexto = lpa.getContexto();
        ((ListaCiudades) contexto).startActivityForResult(intent, ListaCiudades.PETICION1);
    }
}
