package com.codigo.rafael.easygas;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.codigo.rafael.easygas.entities.Cep;
import com.codigo.rafael.easygas.entities.MessageEB;
import com.codigo.rafael.easygas.interfaces.CepService;
import com.codigo.rafael.easygas.service.LocationIntentService;
import com.codigo.rafael.easygas.util.Mask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnderecoAddActivity extends AppCompatActivity {
    //Constantes
    public static String URLBase = "https://viacep.com.br/";
    public static final String LOCATION = "location";
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";

    private Location mLastLocation;


    //Elementos da tela
    private Toolbar toolbar;
    private Button btBuscarCep;
    private LinearLayout llElementos;
    private EditText etCep, etComplemento, etBairro, etLogradouro, etNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_add);

        EventBus.getDefault().register(this);


//        toolbar = findViewById(R.id.tb_endereco_add_activity);
//        toolbar.setTitle("NOVO ENDEREÇO");
//        toolbar.setTitleTextColor(Color.WHITE);
        llElementos = findViewById(R.id.ll_elementos_endereco_add_activity);
        btBuscarCep = findViewById(R.id.bt_buscar_cep_endereco_add_activity);
        etCep = findViewById(R.id.et_cep_endereco_add_activity);
        etComplemento = findViewById(R.id.et_complemento_endereco_add_activity);
        etBairro = findViewById(R.id.et_bairro_endereco_add_activity);
        etLogradouro = findViewById(R.id.et_logradouro_endereco_add_activity);
        etNumero = findViewById(R.id.et_numero_endereco_add_activity);


        llElementos.setVisibility(View.INVISIBLE);

        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etCep.addTextChangedListener(Mask.insert(Mask.MaskType.CEP, etCep));

        btBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarLayout();

                Toast.makeText(EnderecoAddActivity.this, "Pesquisando Cep...", Toast.LENGTH_SHORT).show();


                Gson gg = new GsonBuilder().create();

                Retrofit retro = new Retrofit.Builder()
                        .baseUrl(URLBase)
                        .addConverterFactory(GsonConverterFactory.create(gg))
                        .build();

                CepService servico = retro.create(CepService.class);


                final Call<Cep> ceps = servico.dados(etCep.getText().toString());


                ceps.enqueue(new Callback<Cep>() {
                    @Override
                    public void onResponse(Call<Cep> call, Response<Cep> response) {
                        if (response.isSuccessful()) {
//                                Toast.makeText(DadosActivity.this, response.body().getBairro(), Toast.LENGTH_LONG).show();
                            Log.i("Cep ", response.body().getBairro());
                            etLogradouro.setText(response.body().getLogradouro());
                            etComplemento.setText(response.body().getComplemento());
                            etBairro.setText(response.body().getBairro());
//                            etCidade.setText("Cidade: " + response.body().getLocalidade());
                        }
                    }

                    @Override
                    public void onFailure(Call<Cep> call, Throwable t) {

                    }
                });
            }
        });

    }


    private void mostrarLayout() {
        llElementos.setVisibility(View.VISIBLE);
    }


    public void getLocationListener(View view) {
        mostrarLayout();
        int type;
        String address = null;

        if (view.getId() == R.id.bt_gps_endereco_add_activity) {
            type = 1;
            address = etLogradouro.getText().toString();
        } else {
            type = 2;
        }

        callIntentService(type, address);
    }

    public void callIntentService(int type, String address) {
        Intent it = new Intent(this, LocationIntentService.class);
        it.putExtra(TYPE, type);
        it.putExtra(ADDRESS, address);
        it.putExtra(LOCATION, mLastLocation);
        startService(it);
    }

    public void onEvent(final MessageEB m) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("LOG", m.getResultMessage());
                etLogradouro.setText("Dados: " + m.getResultMessage());
            }
        });
    }

}
