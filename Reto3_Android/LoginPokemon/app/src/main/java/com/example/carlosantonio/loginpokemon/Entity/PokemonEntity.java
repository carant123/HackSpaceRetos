package com.example.carlosantonio.loginpokemon.Entity;

/**
 * Created by Carlos Antonio on 16/02/2016.
 */
public class PokemonEntity {

    // asegurarse de que los datos concuerden con los nombres de nuestra variable
    private int id;
    private String nombre;
    private String tipo;
    private String imagen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
