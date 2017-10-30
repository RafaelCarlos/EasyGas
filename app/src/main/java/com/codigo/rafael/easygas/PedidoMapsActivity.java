package com.codigo.rafael.easygas;

import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class PedidoMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    //    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private MarkerOptions marcador;
    final int MY_PERMISSION_REQUEST_CODE = 7171;
    private String slatlng;
    private static final int REQUEST_CHECK_SETTINGS = 0;
    private double latitude, longitude;
    private static final int DEFAULT_ZOOM = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mSettingsClient = LocationServices.getSettingsClient(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        marcador = new MarkerOptions().draggable(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin_map));


        //LocationResquest com as definições requeridas
        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds
        //Construção dum LocationSettingsRequest com as definições requeridas
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();

        //Callback a ser chamado quando houver alterações na localização
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);

                Location currentLocation = locationResult.getLastLocation();
                handleCurrentLocation(currentLocation);
            }
        };
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

//    protected void onStop() {
//        mGoogleApiClient.disconnect();
//        super.onStop();
//    }

    /*
    * Ao iniciar, connectamos !
    */
//    protected void onStart() {
//        getLocation();
//        mGoogleApiClient.connect();
//        super.onStart();
//    }

//    private void getLocation() {
//
//        // Vamos instanciar o GoogleApiClient, caso seja nulo
//        if (mGoogleApiClient == null) {
//            mGoogleApiClient = new GoogleApiClient.Builder(this)
//                    .addConnectionCallbacks(this) // Interface ConnectionCallbacks
//                    .addOnConnectionFailedListener(this) //Interface OnConnectionFailedListener
//                    .addApi(LocationServices.API) // Vamos a API do LocationServices
//                    .build();
//        }
//    }

//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        // pegamos a ultima localização
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(this, new String[]{
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
//                    android.Manifest.permission.ACCESS_FINE_LOCATION
//            }, MY_PERMISSION_REQUEST_CODE);
//
//        } else {
//            getLocation();
//        }
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if (mLastLocation != null) {
//            if (mMap != null) {
//                // Criamos o LatLng através do Location
//                final LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
//                // Adicionamos um Marker com a posição...
//                mMap.addMarker(new MarkerOptions().draggable(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin_map)).position(latLng).title("Minha Posição" + "\nSegure e arraste para selecionar o lugar correto."));
//                // Um zoom no mapa para a seua posição atual...
//                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
////                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
//
//            }
//
//        }
//    }

    //Inicia o processo de pedido de actualizações de localização
    private void startLocationUpdates() {
        // Verifica se as definições do dispositivo estão configuradas para satisfazer
        // as requeridas pelo LocationSettingsRequest.
        mSettingsClient.checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        // Todas as definições do dispositivo estão configuradas para satisfazer as requeridas.
                        // Inicia o pedido de actualizações de localização

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                // As definições do dispositivo não satisfazem as requeridas.
                                //Mas podem ser alteradas pelo utilizador.
                                try {
                                    // Mostra um dialog chamando startResolutionForResult(),
                                    // o resultado deverá ser verificado em onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(PedidoMapsActivity.this,
                                            REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i("Location", "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                // As definições do dispositivo não satisfazem as requeridas,
                                // não havendo forma de as resolver.
                                String errorMessage = "As definições do dispositivo não " +
                                        "satisfazem as requeridas, altere-as nas Configurações";
                                Toast.makeText(PedidoMapsActivity.this, errorMessage, Toast.LENGTH_LONG)
                                        .show();
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            startLocationUpdates();
        } else if (!checkPermissions()) {
            //Não implementado, apenas necessário se targetSdkVersion >= 23
//            requestPermissions();
            Toast.makeText(this, "Não foi possível obter a localização", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void handleCurrentLocation(Location currentLocation) {

        final LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(marcador.position(latLng).title("Minha Posição" + "\nSegure e arraste para selecionar o lugar correto."));
//        mMap.addMarker(new MarkerOptions().draggable(true).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_pin_map)).position(latLng).title("Minha Posição" + "\nSegure e arraste para selecionar o lugar correto."));
        // Um zoom no mapa para a seua posição atual...
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));

    }

//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//
//    }
}