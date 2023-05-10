package com.example.tfgtours.Recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import com.example.tfgtours.R;
import com.example.tfgtours.Clases.Ciudades;
import com.example.tfgtours.Utilidades.ImagenesFirebase;

public class ListaCiudadAdapter extends RecyclerView.Adapter<CiudadViewHolder> {
    // atributos
    private Context contexto = null;
    private ArrayList<Ciudades> ciudades = null;
    private LayoutInflater inflate = null;

    private FirebaseAuth mAuth;

    public ListaCiudadAdapter(Context contexto, ArrayList<Ciudades> ciudades ) {
        this.contexto = contexto;
        this.ciudades = ciudades;
        inflate =  LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Ciudades> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ArrayList<Ciudades> ciudades) {
        this.ciudades = ciudades;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflate.inflate(R.layout.item_rv_ciudad,parent,false);
        CiudadViewHolder evh = new CiudadViewHolder(mItemView,this);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder holder, int position) {
        Ciudades p = this.getCiudades().get(position);
        //        cargo la imagen desde la base de datos
        //-----------------------------------------------------------------
        String carpeta = p.getNombre();
        ImageView imagen = holder.getImg_item_ciudad() ;
        ImagenesFirebase.descargarFoto(carpeta,p.getNombre(),imagen);
        ImageView imagen1 = imagen;
        holder.setImg_item_ciudad(imagen1);
        //----------------------------------------------------------------------
        holder.getTxt_item_nombre().setText("nombre: " + p.getNombre());

    }


    @Override
    public int getItemCount() {
        return this.ciudades.size();
    }

    public void addCiudad(Ciudades ciudadAñadido) {
        ciudades.add(ciudadAñadido);
        notifyDataSetChanged();
    }
}
