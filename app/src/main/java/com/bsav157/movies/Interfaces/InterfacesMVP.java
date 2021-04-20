package com.bsav157.movies.Interfaces;

public interface InterfacesMVP {

    interface View{
        void showLoading();
        void finishLoading();
        void loadRecycler();
    }

    interface Interactor{
        void downloadData();
    }

    interface Presenter{
        void loadRecycler();
        void showLoading();
        void finishLoading();
        void downloadData();
    }

}
