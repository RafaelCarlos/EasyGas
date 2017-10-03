package com.codigo.rafael.easygas.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.codigo.rafael.easygas.MainActivity;
import com.codigo.rafael.easygas.R;
import com.codigo.rafael.easygas.entities.Usuario;
import com.codigo.rafael.easygas.util.Mask;

import java.util.ArrayList;
import java.util.List;

public class EnderecoCadastroActivity extends AppCompatActivity {
    private EditText etCep;
    private Spinner spEstados;
    private List<String> estados = new ArrayList<>();
    private String estado;
    private Button btConfirmar;

    private Usuario user = new Usuario();
    private Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_cadastro);
        preencheLista();
        user = (Usuario) getIntent().getSerializableExtra("user");

        etCep = (EditText) findViewById(R.id.et_cep_endereco_cadatro_activity);

        spEstados = (Spinner) findViewById(R.id.sp_estados_endereco_cadastro_activity);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, estados);
        btConfirmar = (Button) findViewById(R.id.bt_cadastrar_endereco_cadastro_activity);
        ArrayAdapter<String> spinnerAdapter = arrayAdapter;
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spEstados.setAdapter(spinnerAdapter);

        spEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                estado = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Estado selecionado: " + estado, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etCep.addTextChangedListener(Mask.insert(Mask.MaskType.CEP, etCep));

        btConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putSerializable("userRecebido", user);
                Intent intent = new Intent(EnderecoCadastroActivity.this, MainActivity.class);

                intent.putExtras(bundle);

                startActivity(intent);

//                startActivity(new Intent(EnderecoCadastroActivity.this, MainActivity.class));
            }
        });
    }

    private void preencheLista() {
        estados.add("AC");
        estados.add("AL");
        estados.add("AP");
        estados.add("AM");
        estados.add("BA");
        estados.add("CE");
        estados.add("DF");
        estados.add("ES");
        estados.add("GO");
        estados.add("MA");
        estados.add("MT");
        estados.add("MS");
        estados.add("MG");
        estados.add("PA");
        estados.add("PB");
        estados.add("PE");
        estados.add("PI");
        estados.add("PR");
        estados.add("RJ");
        estados.add("RN");
        estados.add("RO");
        estados.add("RS");
        estados.add("SC");
        estados.add("SE");
        estados.add("SP");
        estados.add("TO");

    }
}
