package com.codigo.rafael.easygas.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.util.Mask;

public class CadastrarUserActivity extends AppCompatActivity {

    private EditText etTelefone;
    private Button btCadastrarEndereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_user);

        etTelefone = (EditText) findViewById(R.id.et_telefone_activity_cadastrar_user);
        btCadastrarEndereco = (Button) findViewById(R.id.btn_signup);
        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etTelefone.addTextChangedListener(Mask.insert(Mask.MaskType.TEL, etTelefone));

        btCadastrarEndereco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastrarUserActivity.this, EnderecoCadastroActivity.class));
            }
        });
    }
}
