package com.codigo.rafael.easygas.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.util.Mask;

public class EnderecoCadastroActivity extends AppCompatActivity {
    private EditText etCep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_cadastro);
        etCep = (EditText) findViewById(R.id.et_cep_activity_dados);


        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etCep.addTextChangedListener(Mask.insert(Mask.MaskType.CEP, etCep));
    }
}
