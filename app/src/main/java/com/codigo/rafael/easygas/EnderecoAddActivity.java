package com.codigo.rafael.easygas;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnderecoAddActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    //Constantes
    public static String URLBase = "https://viacep.com.br/";
    public static final String LOCATION = "location";
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";

    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;


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
        callConnection();

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
                            Log.i("Entrou no  ", response.body().getBairro());

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

    private synchronized void callConnection() {
        Log.i("LOG", "AddressLocationActivity.callConnection()");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("LOG", "AddressLocationActivity.onConnected(" + bundle + ")");

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location l = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "Endereco conectado (" + i + ")");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /*
    @TODO
    private void getCityByLocation(Location location) {
        //obtendo coordenadas
        double latPoint = location.getLatitude();
        double lngPoint = location.getLongitude();

        //Classe que fornece a localização da cidade
        Geocoder geocoder = new Geocoder(this.getApplicationContext());
        List myLocation = null;

        try {
            //Obtendo os dados do endereço
            myLocation = geocoder.getFromLocation(latPoint, lngPoint, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if ( myLocation != null && myLocation.size() > 0) {
            Address a = (Address) myLocation.get(0);
            //Pronto! Vocêm tem o nome da cidade!
            String city = a.getLocality();
            String street = a.getAddressLine(0);
            //Seu código continua aqui...
        } else {
            Log.d("geolocation", "endereço não localizado");
        }
    }
     */

}
