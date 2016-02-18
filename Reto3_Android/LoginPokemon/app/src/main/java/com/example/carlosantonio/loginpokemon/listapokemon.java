package com.example.carlosantonio.loginpokemon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosantonio.loginpokemon.Entity.PokemonEntity;
import com.example.carlosantonio.loginpokemon.services.ApiImplementation;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Carlos Antonio on 17/02/2016.
 */
public class listapokemon extends Activity {

    TextView tvpokemon;
    Bundle bun;
    ListView lvPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listapokemon_layout);
        tvpokemon = (TextView) findViewById(R.id.tvnombredepokemon);

        bun = getIntent().getExtras();
        tvpokemon.setText("Hola " + bun.getString("NombrePokemon"));

        //carga de imagen

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions).build();

        ImageLoader.getInstance().init(config);

        lvPokemon = (ListView) findViewById(R.id.lvPokemonlista);


        //llamada al api

        ApiImplementation.getServices().getPokemons(new Callback<ArrayList<PokemonEntity>>() {

            //si tdo esta bien tiene internet, esta bien armadel proceso esta bien armado
            @Override
            public void success(ArrayList<PokemonEntity> lista, Response response) {
                //success

                List<PokemonEntity> pokemonModelList = new ArrayList<>();

                for (PokemonEntity p : lista) {

                    Log.i("respuesta",p.getNombre());
                    Log.i("respuesta",p.getTipo());
                    Log.i("respuesta",p.getImagen());

                    PokemonEntity pokemonModel = new PokemonEntity();
                    pokemonModel.setId(p.getId());
                    pokemonModel.setNombre(p.getNombre());
                    pokemonModel.setTipo(p.getTipo());
                    pokemonModel.setImagen(p.getImagen());

                    pokemonModelList.add(pokemonModel);
                }


                PokemonAdapter adapter = new PokemonAdapter(getApplicationContext(), R.layout.listapokemon_layout_seccion, pokemonModelList);
                lvPokemon.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Log.i("respuesta","algo salio mal");


            }
        });


    }



    public class PokemonAdapter  extends ArrayAdapter {

        private List<PokemonEntity> pokemonModelList;
        private int resource;
        private LayoutInflater inflator;

        public PokemonAdapter(Context context, int resource, List<PokemonEntity> objects) {
            super(context, resource, objects);
            pokemonModelList = objects;
            //el layout
            this.resource = resource;
            inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null) {
                holder = new ViewHolder();
                convertView = inflator.inflate(resource, null);
                holder.VhivPokemon = (ImageView) convertView.findViewById(R.id.ivPokemon);
                holder.VhtvPokemon = (TextView) convertView.findViewById(R.id.tvnombrepokemonseccion);
                holder.VhtvTipoPokemon = (TextView) convertView.findViewById(R.id.tvtipopokemonseccion);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }


            try {

                holder.VhtvPokemon.setText(pokemonModelList.get(position).getNombre());
                holder.VhtvTipoPokemon.setText("Tipo: " + pokemonModelList.get(position).getTipo());




            final ProgressBar progressbar = (ProgressBar) convertView.findViewById(R.id.progressBar);

            //entonces cuando tu quieres mostrar una imagen
            ImageLoader.getInstance().displayImage(pokemonModelList.get(position).getImagen(), holder.VhivPokemon, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    progressbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    progressbar.setVisibility(View.GONE);
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    progressbar.setVisibility(View.GONE);
                }
            });


        } catch (Exception e) {

            //Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
           // holder.VhtvPokemon2.setText("Hola");
            //holder.VhtvTipoPokemon.setText("Tipo: " + pokemonModelList.get(position).getTipo());

            return convertView;
        }

        class ViewHolder{
            private ImageView VhivPokemon;
            private TextView VhtvPokemon;
            private TextView VhtvTipoPokemon;

        }


    }
}
