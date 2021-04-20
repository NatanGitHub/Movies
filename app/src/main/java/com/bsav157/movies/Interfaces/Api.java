package com.bsav157.movies.Interfaces;

import com.bsav157.movies.ModelMovies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("movie?api_key=9ae7ddebe61cc64f72709f11c9ecda35&language=es&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_watch_monetization_types=flatrate")
    Call<ModelMovies> getData();

}
