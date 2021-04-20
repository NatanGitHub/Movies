package com.bsav157.movies.Interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.bsav157.movies.Adapters.AdapterMovies;
import com.bsav157.movies.Interfaces.Api;
import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.ModelMovies;
import com.bsav157.movies.View.Extras;
import com.bsav157.movies.View.MainActivityView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Interactor implements InterfacesMVP.Interactor {

    private InterfacesMVP.Presenter presenter;
    private Context context;

    public Interactor(InterfacesMVP.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void downloadData(Context context) {
        this.context = context;
        new DownloadDataInBackground().execute();
    }

    class DownloadDataInBackground extends AsyncTask<String, Integer, Integer>{

        @Override
        protected Integer doInBackground(String... strings) {

            // Justo aqui debo validar que haya conexion a internet
            if(!Extras.isOnline(context)){
                presenter.isOffline();
                return null;
            }

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/discover/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api api = retrofit.create(Api.class);

            Call<ModelMovies> modelMoviesCall = api.getData();
            modelMoviesCall.enqueue(new Callback<ModelMovies>() {
                @Override
                public void onResponse(Call<ModelMovies> call, Response<ModelMovies> response) {
                    if(response.isSuccessful())
                        presenter.loadRecycler(response.body());
                    else
                        presenter.onDownloadFailure(response.message());
                }

                @Override
                public void onFailure(Call<ModelMovies> call, Throwable t) {
                    presenter.onDownloadFailure(t.getMessage());
                }
            });

            return null;
        }
    }

}
