package com.bsav157.movies.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bsav157.movies.Adapters.AdapterMovies;
import com.bsav157.movies.Extras;
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
    private Dialog dialogLoading;
    private Context context = this;
    // Elementos del Constrain "constrain_offline"
    private ConstraintLayout constrainOffline;
    private Button botonReintentar;
    private ImageView imageMessage;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
    }

    void initItems(){
        presenter = new Presenter(this);
        recyclerMovies = findViewById(R.id.recycler_carga);
        layoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);

        dialogLoading = new Dialog(context);
        dialogLoading.setContentView(R.layout.loading);

        // Zona para elementos del Constrain "constrain_offline"
        constrainOffline = findViewById(R.id.constrain_offline);
        botonReintentar = findViewById(R.id.boton_reintentar_descarga);
        imageMessage = findViewById(R.id.imageView);
        textViewMessage = findViewById(R.id.text_view_message);

        presenter.downloadData(context);
    }

    @Override
    public void showLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialogLoading.show();
            }
        });
    }

    @Override
    public void finishLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                dialogLoading.dismiss();
            }
        });
    }

    @Override
    public void isOffline() {

        textViewMessage.setText( R.string.offline );
        imageMessage.setBackgroundResource( R.drawable.ic_offline );
        botonReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Extras.isOnline(context)){
                    Toast.makeText(context, R.string.offline, Toast.LENGTH_SHORT).show();
                    return;
                }
                constrainOffline.setVisibility(View.GONE);
                presenter.downloadData(context);
            }
        });

        constrainOffline.setVisibility(View.VISIBLE);
        System.out.println("SIN INTERNET");
    }

    @Override
    public void onDownloadFailure(String message) {

        textViewMessage.setText( R.string.service_break );
        imageMessage.setBackgroundResource( R.drawable.ic_service_break );
        botonReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Extras.isOnline(context)){
                    Toast.makeText(context, R.string.offline, Toast.LENGTH_SHORT).show();
                    return;
                }
                constrainOffline.setVisibility(View.GONE);
                presenter.downloadData(context);
            }
        });

        constrainOffline.setVisibility(View.VISIBLE);
        System.out.println("Error de conexion: " + message);
    }

    @Override
    public void loadRecycler(ModelMovies modelMovies) {
        adapterMovies = new AdapterMovies(modelMovies, getApplicationContext());
        recyclerMovies.setHasFixedSize(false);
        recyclerMovies.setLayoutManager(layoutManager);
        recyclerMovies.setAdapter(adapterMovies);

        constrainOffline.setVisibility(View.GONE);
        recyclerMovies.setVisibility(View.VISIBLE);
    }
}