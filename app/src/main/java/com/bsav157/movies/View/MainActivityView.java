package com.bsav157.movies.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.bsav157.movies.Adapters.AdapterMovies;
import com.bsav157.movies.Interfaces.Api;
import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.ModelMovies;
import com.bsav157.movies.Presenter.Presenter;
import com.bsav157.movies.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivityView extends AppCompatActivity implements InterfacesMVP.View {

    private InterfacesMVP.Presenter presenter;
    private RecyclerView recyclerMovies;
    private AdapterMovies adapterMovies;
    private GridLayoutManager layoutManager;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
        presenter.downloadData();
    }

    void initItems(){
        presenter = new Presenter(this);
        recyclerMovies = findViewById(R.id.recycler_carga);
        layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void finishLoading() {

    }

    @Override
    public void isOffline() {

    }

    @Override
    public void onDownloadFailure() {

    }

    @Override
    public void loadRecycler(ModelMovies modelMovies) {
        adapterMovies = new AdapterMovies(modelMovies, getApplicationContext());
        recyclerMovies.setHasFixedSize(false);
        recyclerMovies.setLayoutManager(layoutManager);
        recyclerMovies.setAdapter(adapterMovies);
    }
}