package com.codigo.rafael.easygas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class DistribuidorActivity extends AppCompatActivity {

    private TextView tvTituloNome, tvBairro, tvDistancia;
    private RatingBar rbAvaliacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribuidor);
    }
}
