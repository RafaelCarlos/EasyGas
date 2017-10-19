package com.codigo.rafael.easygas;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PedidoMapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    final int MY_PERMISSION_REQUEST_CODE = 7171;
    private String slatlng;
    private double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//mGoogleApiClient.connect();
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-10.1867812, -48.2988085);
//        mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /*
    * Ao iniciar, connectamos !
    */
    protected void onStart() {
        getLocation();
        mGoogleApiClient.connect();
        super.onStart();
    }

    private void getLocation() {

        // Vamos instanciar o GoogleApiClient, caso seja nulo
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this) // Interface ConnectionCallbacks
                    .addOnConnectionFailedListener(this) //Interface OnConnectionFailedListener
                    .addApi(LocationServices.API) // Vamos a API do LocationServices
                    .build();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // pegamos a ultima localização
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
            }, MY_PERMISSION_REQUEST_CODE);

        } else {
            getLocation();
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            if (mMap != null) {
                // Criamos o LatLng através do Location
                final LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                // Adicionamos um Marker com a posição...
                mMap.addMarker(new MarkerOptions().draggable(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin_map)).position(latLng).title("Minha Posição"));
                // Um zoom no mapa para a seua posição atual...
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

            }

        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}