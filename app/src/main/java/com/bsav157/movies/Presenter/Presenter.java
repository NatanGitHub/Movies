package com.bsav157.movies.Presenter;

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

    }

    @Override
    public void onDownloadFailure() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void finishLoading() {
        if(view != null){

        }
    }

    @Override
    public void downloadData() {
        if(interactor != null){
            interactor.downloadData();
        }
    }
}
