package com.bsav157.movies.Presenter;

import com.bsav157.movies.Interactor.Interactor;
import com.bsav157.movies.Interfaces.InterfacesMVP;

public class Presenter implements InterfacesMVP.Presenter {

    private InterfacesMVP.View view;
    private InterfacesMVP.Interactor interactor;

    public Presenter(InterfacesMVP.View view){
        this.view = view;
        interactor = new Interactor(this);
    }

    @Override
    public void loadRecycler() {

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

        }
    }
}
