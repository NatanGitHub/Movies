package com.bsav157.movies.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bsav157.movies.Extras;
import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.ModelMovies;
import com.bsav157.movies.R;
import com.bumptech.glide.Glide;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolder> {

    private ModelMovies modelMovies;
    private Context context;
    private InterfacesMVP.Presenter presenter;

    public AdapterMovies(ModelMovies modelMovies, Context context, InterfacesMVP.Presenter presenter){
        this.modelMovies = modelMovies;
        this.context = context;
        this.presenter = presenter;
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
        holder.cardMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.showDetailsMovie(modelMovies.getResults().get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelMovies.getResults().size();
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
                    .into(posterMovie);
        }

    }
}
