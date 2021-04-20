package com.bsav157.movies.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import com.bsav157.movies.Interfaces.InterfacesMVP;
import com.bsav157.movies.Presenter.Presenter;
import com.bsav157.movies.R;

public class MainActivityView extends AppCompatActivity implements InterfacesMVP.View {

    private InterfacesMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initItems();
    }

    void initItems(){
        presenter = new Presenter(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void finishLoading() {

    }

    @Override
    public void loadRecycler() {

    }
}