package com.example.carlosantonio.loginpokemon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carlosantonio.loginpokemon.Entity.PokemonEntity;
import com.example.carlosantonio.loginpokemon.services.ApiImplementation;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Carlos Antonio on 16/02/2016.
 */
public class main_activity extends Activity implements View.OnClickListener {

    EditText etN, etTP;
    Button btS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etN = (EditText) findViewById(R.id.etName);
        etTP = (EditText) findViewById(R.id.etTipoPokemon);
        btS = (Button) findViewById(R.id.btSumit);



        btS.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        final String datanombre = etN.getText().toString();
        final String dataTipoPokemon = etTP.getText().toString();


        ApiImplementation.getServices().getPokemons(new Callback<ArrayList<PokemonEntity>>() {

            //si tdo esta bien tiene internet, esta bien armadel proceso esta bien armado
            @Override
            public void success(ArrayList<PokemonEntity> lista, Response response) {
                //success
                for (PokemonEntity p : lista){
                    //Log.i("respuesta",p.getNombre());
                    //Log.i("respuesta",p.getTipo());
                    //Log.i("respuesta",p.getImagen());

                    if ( datanombre.equals(p.getNombre()) && dataTipoPokemon.equals(p.getTipo()) ){
                        Intent i = new Intent(main_activity.this,listapokemon.class);
                        Bundle a = new Bundle();
                        a.putString("NombrePokemon",p.getNombre());
                        i.putExtras(a);
                        startActivity(i);

                        break;

                    }

                }
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i("respuesta","algo salio mal");
            }
        });



    }
}
