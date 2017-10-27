package com.codigo.rafael.easygas;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PedidoActivity extends AppCompatActivity {

    private Toolbar tbPedido;
    private Button btPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        tbPedido = findViewById(R.id.tb_pedido_activity);
        btPedido = findViewById(R.id.bt_finalizar_pedido_activity_pedido);

        setSupportActionBar(tbPedido);


        tbPedido.setTitle("Meu Pedido");
        tbPedido.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PedidoActivity.this, PedidoMapsActivity.class));
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
