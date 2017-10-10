package com.codigo.rafael.easygas;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.codigo.rafael.easygas.entities.Cep;
import com.codigo.rafael.easygas.entities.MessageEB;
import com.codigo.rafael.easygas.entities.Results;
import com.codigo.rafael.easygas.interfaces.CepService;
import com.codigo.rafael.easygas.interfaces.ResultsService;
import com.codigo.rafael.easygas.service.LocationIntentService;
import com.codigo.rafael.easygas.util.Mask;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnderecoAddActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    //Constantes
    public static String URLBase = "https://viacep.com.br/";
    public static String URLGeocoder = "https://maps.googleapis.com/maps/api/geocode/";
    public static final String LOCATION = "location";
    public static final String TYPE = "type";
    public static final String ADDRESS = "address";
    private static final String CHAVE = " AIzaSyDemBk7LhpI0FcBcpzK3x7ALyu5wqUjAko ";
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;

    private String slatlng;

    private MaterialDialog dialog;
    //Elementos da tela
    private Toolbar toolbar;
    private Button btBuscarCep, btBuscaLocalizacao;
    private LinearLayout llElementos;
    private EditText etCep, etComplemento, etBairro, etLogradouro, etNumero, etLatitude, etLongitude;
    private View viewInclude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endereco_add);


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
        etLatitude = findViewById(R.id.et_latitude_endereco_add_activity);
        etLongitude = findViewById(R.id.et_longintude_endereco_add_activity);
        btBuscaLocalizacao = findViewById(R.id.bt_gps_endereco_add_activity);


        llElementos.setVisibility(View.INVISIBLE);


        // Vamos instanciar o GoogleApiClient, caso seja nulo
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this) // Interface ConnectionCallbacks
                    .addOnConnectionFailedListener(this) //Interface OnConnectionFailedListener
                    .addApi(LocationServices.API) // Vamos a API do LocationServices
                    .build();
        }


        //Adicionando um TextWatcher do tipo TEL(Telefone) em um EditText.
        etCep.addTextChangedListener(Mask.insert(Mask.MaskType.CEP, etCep));


        btBuscaLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llElementos.setVisibility(View.VISIBLE);
                mGoogleApiClient.connect();
                dialog = new MaterialDialog.Builder(EnderecoAddActivity.this)
                        .title("")
                        .content("Pesquisando Endereço" + "\nPor favor, aguarde...")
                        .icon(getDrawable(R.mipmap.ic_easygas))
                        .contentColorRes(R.color.colorAccent)
                        .canceledOnTouchOutside(false)
                        .progress(true, 0)
                        .show();
                Gson gg = new GsonBuilder().create();

                Retrofit retro = new Retrofit.Builder()
                        .baseUrl(URLGeocoder)
                        .addConverterFactory(GsonConverterFactory.create(gg))
                        .build();

                ResultsService servico = retro.create(ResultsService.class);


                final Call<Results> results = servico.dadosEndereco("json?" + slatlng);

                results.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {
                        if (response.isSuccessful()) {
                            etLogradouro.setText(response.body().getFormatedAddress());
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {
                        Toast.makeText(EnderecoAddActivity.this, "Não possível obter o endereço.", Toast.LENGTH_LONG).show();
                        Log.i("Erro GPS", t.toString());
                        dialog.dismiss();
                    }
                });

            }
        });

        btBuscarCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mostrarLayout();
                llElementos.setVisibility(View.VISIBLE);
//                llElementosCep.setVisibility(View.VISIBLE);
//                llElementosGps.setVisibility(View.INVISIBLE);

                dialog = new MaterialDialog.Builder(EnderecoAddActivity.this)
                        .title("")
                        .content("Pesquisando Cep" + "\nPor favor, aguarde...")
                        .icon(getDrawable(R.mipmap.ic_easygas))
                        .contentColorRes(R.color.colorAccent)
                        .canceledOnTouchOutside(false)
                        .progress(true, 0)
                        .show();

//                Toast.makeText(EnderecoAddActivity.this, "Pesquisando Cep...", Toast.LENGTH_SHORT).show();


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
                            dialog.dismiss();

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
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    /*
     * Método invocado quando o GoogleApiClient conseguir se conectar
     */
    @Override
    public void onConnected(Bundle bundle) {
        // pegamos a ultima localização
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {

            // Criamos o LatLng através do Location
            final LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            etLatitude.setText(String.valueOf(mLastLocation.getLatitude()));
            etLongitude.setText(String.valueOf(mLastLocation.getLongitude()));
            slatlng = etLatitude.getText().toString() + "," + etLongitude.getText().toString();
//            etLatitude.setText(latLng.toString());
            // Adicionamos um Marker com a posição...


        }
    }
    /*
   * Ao finalizar, desconectamos!
  */

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /*
    * Ao iniciar, connectamos !
    */
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    /*
    Método responsável por fazer a verificação de quando view está visível.
     */
    private void mostrarLayout() {
        llElementos.setVisibility(View.VISIBLE);
//        if (!llElementosCep.isShown()) {
//            llElementosCep.setVisibility(View.VISIBLE);
//            llElementosGps.setVisibility(View.INVISIBLE);
//            boolean i = llElementos.isShown();
//            Toast.makeText(this, "Visibilidade " + i, Toast.LENGTH_SHORT).show();
//        } else if (!llElementosGps.isShown()) {
//            llElementosGps.setVisibility(View.VISIBLE);
//            llElementosCep.setVisibility(View.INVISIBLE);
//
//        }
    }

//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        Log.i("LOG", "AddressLocationActivity.onConnected(" + bundle + ")");
//
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location l = LocationServices
//                .FusedLocationApi
//                .getLastLocation(mGoogleApiClient);
//    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "Endereco conectado (" + i + ")");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Não foi possível obter a localização", Toast.LENGTH_LONG).show();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        etLatitude.setText(String.valueOf(location.getLatitude()));
        etLongitude.setText(String.valueOf(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

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
