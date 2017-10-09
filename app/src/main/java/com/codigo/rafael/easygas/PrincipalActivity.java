package com.codigo.rafael.easygas;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class PrincipalActivity extends AppCompatActivity implements LocationListener {

    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvDescricao;
    LocationManager locationManager;
    String provider;
    final int MY_PERMISSION_REQUEST_CODE = 7171;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        tvLongitude = (TextView) findViewById(R.id.tvLongitude);
        tvLatitude = (TextView) findViewById(R.id.tvLatitude);
        tvDescricao = (TextView) findViewById(R.id.tvDescricao);

        LocationManager lm = (LocationManager)
                this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);
        } else {
            getLocation();
        }

//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    getLocation();
                break;

        }
    }

    private void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);


        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        final Location location = locationManager.getLastKnownLocation(provider);
        if (location == null)
            Log.e("ERROR", "Location is null");
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        tvLatitude.setText(String.valueOf(latitude));
        tvLongitude.setText(String.valueOf(longitude));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void btBuscarDescricaoOnClick(View v) {
        new Conexao().execute(tvLatitude.getText().toString(),
                tvLongitude.getText().toString());
    }

    class Conexao extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                String url = "http://maps.googleapis.com/maps/api/geocode/xml?latlng="
                        + params[0] + "," + params[1];

                URL link = new URL(url);
                URLConnection urlConnection = link.openConnection();
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpGet request = new HttpGet(url);
//                InputStream in = httpclient.execute(request).getEntity()
//                        .getContent();

                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();

                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    line = br.readLine();

                }

                String resposta = sb.toString();

                return resposta.substring(
                        resposta.indexOf("<formatted_address>") + 19,
                        resposta.indexOf("</formatted_address>"));

            } catch (Exception e) {
                return "Erro: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String msg) {
            tvDescricao.setText(msg);
        }

    }
}
