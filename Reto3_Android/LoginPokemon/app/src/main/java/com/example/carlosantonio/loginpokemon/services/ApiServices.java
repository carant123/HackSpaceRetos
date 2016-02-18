package com.example.carlosantonio.loginpokemon.services;

import com.example.carlosantonio.loginpokemon.Entity.PokemonEntity;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Carlos Antonio on 16/02/2016.
 */
public interface ApiServices {
    //se escribe el nombre del documento almacenados en el API
    @GET("/lista_pokemons.php")
    //se crea un metodo para llamar al tipo de archivos arraylist de tipo Pokemon entity o el modelo
    void getPokemons(Callback<ArrayList<PokemonEntity>> callback);
}
