package com.codigo.rafael.easygas.service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.codigo.rafael.easygas.EnderecoAddActivity;
import com.codigo.rafael.easygas.entities.MessageEB;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rafael Carlos Oliveira on 04/10/2017.
 */
public class LocationIntentService extends IntentService {


    public LocationIntentService() {
        super("worker_thread");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Location location = intent.getParcelableExtra(EnderecoAddActivity.LOCATION);
        int type = intent.getIntExtra(EnderecoAddActivity.TYPE, 1);
        String address = intent.getStringExtra(EnderecoAddActivity.ADDRESS);

        List<Address> list = new ArrayList<Address>();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String error = "";
        String resultAddress = "";


        try {
            if (type == 2 || address == null) {
                list = (ArrayList<Address>) geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } else {
                list = (ArrayList<Address>) geocoder.getFromLocationName(address, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
            error = "Network problem";
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            error = "Illegal arguments";
        }


        if (list != null && list.size() > 0) {
            Address a = list.get(0);


            if (type == 2 || address == null) {
                for (int i = 0, tam = a.getMaxAddressLineIndex(); i < tam; i++) {
                    resultAddress += a.getAddressLine(i);
                    resultAddress += i < tam - 1 ? ", " : "";
                }
            } else {
                resultAddress += a.getLatitude() + "\n";
                resultAddress += a.getLongitude();
            }
        } else {
            resultAddress = error;
        }


        MessageEB m = new MessageEB();
        m.setResultMessage(resultAddress);

        EventBus.getDefault().post(m);
    }
}
