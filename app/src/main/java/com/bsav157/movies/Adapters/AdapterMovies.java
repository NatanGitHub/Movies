package com.bsav157.movies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsav157.movies.ModelMovies;
import com.bsav157.movies.R;
import com.bumptech.glide.Glide;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {

    private ModelMovies modelMovies;
    private Context context;
    String baseUrlImages = "https://image.tmdb.org/t/p/w500";

    public AdapterMovies(ModelMovies modelMovies, Context context){
        this.modelMovies = modelMovies;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMovies.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_movie, parent, false);
        return new AdapterMovies.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovies.ViewHolder holder, int position) {
        holder.loadData( modelMovies.getResults().get(position) );
    }

    @Override
    public int getItemCount() {
        return modelMovies.getResults().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView posterMovie;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterMovie = itemView.findViewById(R.id.poster_movie);
        }

        void loadData(ModelMovies.Results results){
            Glide.with(context)
                    .load(baseUrlImages + results.getPoster_path())
                    //.override(200, 250)
                    .into(posterMovie);
        }

    }
}
