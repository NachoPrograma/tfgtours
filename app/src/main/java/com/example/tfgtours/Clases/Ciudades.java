package com.example.tfgtours.Clases;
import android.graphics.Bitmap;
import java.io.Serializable;
import java.util.Objects;
public class Ciudades {


    public class Ciudades implements Serializable {
        private String nombre;
        private String descripcion;


        public Ciudades(String nombre, String descripcion) {
            this.nombre = nombre;
            this.descripcion = descripcion;
        }

        public Ciudades() {
            this.nombre = "sin nombre";
            this.descripcion = "sin descripcion";
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Ciudades)) return false;
            Ciudades ciudades = (Ciudades) o;
            return nombre.equals(ciudades.nombre);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nombre);
        }

        @Override
        public String toString() {
            return "Ciudad{" +
                    "nombre='" + nombre + '\'' +
                    ", descripcion='" + descripcion + '\'' +
                    '}';
        }
    }
}
