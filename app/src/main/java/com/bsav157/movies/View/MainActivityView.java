package com.bsav157.movies.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.bsav157.movies.Adapters.AdapterMovies;
import com.bsav157.movies.Extras;
import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.ModelMovies;
import com.bsav157.movies.Presenter.Presenter;
import com.bsav157.movies.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class MainActivityView extends AppCompatActivity implements InterfacesMVP.View, SearchView.OnQueryTextListener {

    private Context context = this;

    private InterfacesMVP.Presenter presenter = new Presenter(this);
    private RecyclerView recyclerMovies;
    private AdapterMovies adapterMovies;
    private ImageView exit;
    private GridLayoutManager layoutManager;
    private Dialog dialogLoading, dialogDetailsMovie;
    private SearchView searchViewMovie;
    // Elementos del Constrain "constrain_offline"
    private ConstraintLayout constrainOffline;
    private Button botonReintentar;
    private ImageView imageMessage;
    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideNotificationBar();
        initItems();
        initDialogDetailsMovie();
        initDialogLoading();
        initItemsConstrainOffline();
    }

    void hideNotificationBar(){
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    void initItems(){
        recyclerMovies = findViewById(R.id.recycler_carga);
        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        searchViewMovie = findViewById(R.id.search_movie);
        searchViewMovie.setOnQueryTextListener(this);
        layoutManager = new GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false);

        presenter.downloadData(context);
    }

    void initDialogDetailsMovie(){
        dialogDetailsMovie = new Dialog(context);
        dialogDetailsMovie.setContentView(R.layout.show_details_movie);
        Extras.sizeDialog(dialogDetailsMovie);
    }

    void initDialogLoading(){
        dialogLoading = new Dialog(context);
        dialogLoading.setContentView(R.layout.loading);
        dialogLoading.setCancelable(false);
    }

    void initItemsConstrainOffline(){
        constrainOffline = findViewById(R.id.constrain_offline);
        botonReintentar = findViewById(R.id.boton_reintentar_descarga);
        imageMessage = findViewById(R.id.imageView);
        textViewMessage = findViewById(R.id.text_view_message);
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
        adapterMovies = new AdapterMovies(modelMovies.getResults(), getApplicationContext(), presenter);
        recyclerMovies.setHasFixedSize(false);
        recyclerMovies.setLayoutManager(layoutManager);
        recyclerMovies.setAdapter(adapterMovies);

        constrainOffline.setVisibility(View.GONE);
        searchViewMovie.setVisibility(View.VISIBLE);
        recyclerMovies.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDetailsMovie(ModelMovies.Results modelMovie) {

        ImageView imageViewBackdrop;
        TextView textViewDescription, textViewTitle;

        imageViewBackdrop = dialogDetailsMovie.findViewById(R.id.image_backdrop_path);
        textViewDescription = dialogDetailsMovie.findViewById(R.id.description_movie);
        textViewTitle = dialogDetailsMovie.findViewById(R.id.title_movie);

        Glide.with(context)
                .load(Extras.baseUrlImages + modelMovie.getBackdrop_path())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageViewBackdrop);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Extras.baseUrlImages + modelMovie.getBackdrop_path());
            }
        });

        textViewTitle.setText( modelMovie.getTitle() );
        textViewDescription.setText( modelMovie.getOverview() );

        dialogDetailsMovie.show();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapterMovies.filter(newText);
        return false;
    }
}