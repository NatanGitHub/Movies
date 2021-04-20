package com.bsav157.movies.Interfaces;

import android.content.Context;

import com.bsav157.movies.ModelMovies;

public interface InterfacesMVP {

    interface View{
        void showLoading();
        void finishLoading();
        void isOffline();
        void onDownloadFailure(String message);
        void loadRecycler(ModelMovies modelMovies);
    }

    interface Interactor{
        void downloadData(Context context);
    }

    interface Presenter{
        void loadRecycler(ModelMovies modelMovies);
        void isOffline();
        void onDownloadFailure(String message);
        void showLoading();
        void finishLoading();
        void downloadData(Context context);
    }

}
