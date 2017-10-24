package com.codigo.rafael.easygas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class PedidoActivity extends AppCompatActivity {

    private Toolbar tbPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        tbPedido = findViewById(R.id.tb_pedido_activity);

        setSupportActionBar(tbPedido);


        tbPedido.setTitle("Meu Pedido");
        tbPedido.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
