package com.codigo.rafael.easygas.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by Rafael Carlos Oliveira on 31/10/2017.
 */

public class GcmIntentService extends IntentService {
    private static final int ID_NOTICACAO = 1;
    private NotificationManager objNotificationManager;
    private NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);

        String tipoMsg = gcm.getMessageType(intent);



    }
}
