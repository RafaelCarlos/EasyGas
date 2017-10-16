package com.codigo.rafael.easygas;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codigo.rafael.easygas.entities.Menu;

public class DistribuidorActivity extends AppCompatActivity {

    private TextView tvTituloNome, tvBairro, tvDistancia, tvValor;
    private RatingBar rbAvaliacao;
    private Menu menu;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribuidor);


        tvTituloNome = findViewById(R.id.tv_titulo_nome_distribuidor_activity);
        tvDistancia = findViewById(R.id.tv_distancia_distribuidor_activity);
        tvBairro = findViewById(R.id.tv_bairro_distribuidor_activity);

        coordinatorLayout = findViewById(R.id.coordinator_distribuidor);
        tvValor = findViewById(R.id.tv_valor_distribuidor_activity);

        rbAvaliacao = findViewById(R.id.rbar_avaliacao_distribuidor_activity);

        menu = (Menu) getIntent().getSerializableExtra("menu");

        Log.i("MenuRece", menu.toString());

        final Snackbar snackbar = Snackbar.make(coordinatorLayout, "Dados Recebidos", Snackbar.LENGTH_INDEFINITE);

        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });

        snackbar.show();
        tvTituloNome.setText(menu.getTitulo());
        tvBairro.setText(menu.getBairro());
        tvDistancia.setText(String.valueOf(menu.getDistancia()));
        tvValor.setText(menu.getValor());
        rbAvaliacao.setRating(menu.getAvaliacao());

    }

    /**
     * Exemplo SnackBar
     * @param text

    private void showSnackbar(final String text) {
    View container = findViewById(android.R.id.content);
    if (container != null) {
    Snackbar.make(container, text, Snackbar.LENGTH_LONG).show();
    }
    }
     */
}
