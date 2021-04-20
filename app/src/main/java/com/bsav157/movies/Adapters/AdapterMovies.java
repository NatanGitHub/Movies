package com.bsav157.movies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bsav157.movies.Extras;
import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.ModelMovies;
import com.bsav157.movies.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.ArrayList;
import java.util.List;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {

    private List<ModelMovies.Results> modelMovies;
    private List<ModelMovies.Results> modelMoviesOrigin;
    private Context context;
    private InterfacesMVP.Presenter presenter;

    public AdapterMovies(List<ModelMovies.Results> modelMovies, Context context, InterfacesMVP.Presenter presenter){
        this.modelMovies = modelMovies;
        this.context = context;
        this.presenter = presenter;

        modelMoviesOrigin = new ArrayList<>();
        modelMoviesOrigin.addAll(modelMovies);
    }

    public void filter(String busqueda){
        if(busqueda.length() == 0){
            modelMovies.clear();
            modelMovies.addAll(modelMoviesOrigin);
            notifyDataSetChanged();
            return;
        }

        modelMovies.clear();
        for (ModelMovies.Results i: modelMoviesOrigin ) {
            if (i.getTitle().toLowerCase().contains(busqueda)){
                modelMovies.add(i);
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_movie, parent, false);
        return new AdapterMovies.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovies.ViewHolder holder, int position) {
        holder.loadData( modelMovies.get(position) );
        holder.cardMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDetailsMovie(modelMovies.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView posterMovie;
        CardView cardMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterMovie = itemView.findViewById(R.id.poster_movie);
            cardMovie = itemView.findViewById(R.id.card_movie);
        }

        void loadData(ModelMovies.Results results){
            Glide.with(context)
                    .load(Extras.baseUrlImages + results.getPoster_path())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(posterMovie);
        }

    }
}
