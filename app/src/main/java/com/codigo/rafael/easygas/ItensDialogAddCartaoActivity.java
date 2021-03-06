package com.codigo.rafael.easygas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codigo.rafael.easygas.util.CreditCardFormatTextWatcher;
import com.codigo.rafael.easygas.util.Mask;

/**
 * Created by Rafael Carlos Oliveira on 23/10/2017.
 */

public class ItensDialogAddCartaoActivity extends AppCompatActivity {

    private EditText etNomeTitular, etNumeroCartao, etValidade, etCodigoSeguranca, etCpfTitular;
    private Button btConfirmar;
    private CreditCardFormatTextWatcher tv;
    private Toolbar tbItens;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itens_dialog_add_cartao_activity);

        etNomeTitular = findViewById(R.id.et_numero_cartao_itens_add_cartao);
        etNumeroCartao = findViewById(R.id.et_numero_cartao_itens_add_cartao);
        etValidade = findViewById(R.id.et_data_validade_itens_dialog_add_cartao);
        etCodigoSeguranca = findViewById(R.id.et_cvc_itens_dialog_add_cartao);
        btConfirmar = findViewById(R.id.bt_confirmar_itens_add_cartao);
        tbItens = findViewById(R.id.tb_itens_add_cartao);
        etCpfTitular = findViewById(R.id.et_cpf_itens_dialog_add_cartao);

        setSupportActionBar(tbItens);

        tbItens.setTitle("Add Novo Cartão");
        tbItens.setTitleTextColor(Color.WHITE);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // the EditText here is used to compute the padding (1 EM) which depends
        // on the actual size of the Text rendered on screen taking into account:
        // the font, the text size, the user scaling on text
        tv = new CreditCardFormatTextWatcher(etNumeroCartao);
        etNumeroCartao.addTextChangedListener(tv);

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        etValidade.addTextChangedListener(Mask.insert(Mask.MaskType.VALIDADE, etValidade));
        etCpfTitular.addTextChangedListener(Mask.insert(Mask.MaskType.CPF, etCpfTitular));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
