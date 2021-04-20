package com.bsav157.movies.Presenter;

import android.content.Context;

import com.bsav157.movies.Interactor.Interactor;
import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.ModelMovies;

public class Presenter implements InterfacesMVP.Presenter {

    private InterfacesMVP.View view;
    private InterfacesMVP.Interactor interactor;

    public Presenter(InterfacesMVP.View view){
        this.view = view;
        interactor = new Interactor(this);
    }

    @Override
    public void loadRecycler(ModelMovies modelMovies) {
        if(view != null){
            view.loadRecycler(modelMovies);
        }
    }

    @Override
    public void isOffline() {
        if(view != null){
            view.isOffline();
        }
    }

    @Override
    public void onDownloadFailure(String message) {
        if(view != null){
            view.onDownloadFailure(message);
        }
    }

    @Override
    public void showLoading() {
        if(view != null){
            view.showLoading();
        }
    }

    @Override
    public void finishLoading() {
        if(view != null){
            view.finishLoading();
        }
    }

    @Override
    public void downloadData(Context context) {
        if(interactor != null){
            interactor.downloadData(context);
        }
    }
}
