package com.bsav157.movies.Interfaces;

import com.bsav157.movies.ModelMovies;

public interface InterfacesMVP {

    interface View{
        void showLoading();
        void finishLoading();
        void isOffline();
        void onDownloadFailure();
        void loadRecycler(ModelMovies modelMovies);
    }

    interface Interactor{
        void downloadData();
    }

    interface Presenter{
        void loadRecycler(ModelMovies modelMovies);
        void isOffline();
        void onDownloadFailure();
        void showLoading();
        void finishLoading();
        void downloadData();
    }

}
