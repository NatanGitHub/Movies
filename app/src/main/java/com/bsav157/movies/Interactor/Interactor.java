package com.bsav157.movies.Interactor;

import com.bsav157.movies.Interfaces.InterfacesMVP;

public class Interactor implements InterfacesMVP.Interactor {

    private InterfacesMVP.Presenter presenter;

    public Interactor(InterfacesMVP.Presenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void downloadData() {

    }
}
