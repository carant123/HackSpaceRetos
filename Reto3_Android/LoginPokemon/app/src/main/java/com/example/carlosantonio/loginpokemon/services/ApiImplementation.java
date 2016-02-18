package com.example.carlosantonio.loginpokemon.services;

import retrofit.RestAdapter;

/**
 * Created by Carlos Antonio on 16/02/2016.
 */
public class ApiImplementation {

    public static RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://victorcasass.com/api/")
            .build();

    public static ApiServices getServices(){
        return restAdapter.create(ApiServices.class);
    };



}
