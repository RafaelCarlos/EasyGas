package com.codigo.rafael.easygas.service;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.codigo.rafael.easygas.R;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Rafael Carlos Oliveira on 31/10/2017.
 */

/**
 * Essa é a classe que ficará responsável em receber as mensagens do Firebase,
 * ou seja, a partir dela, a nossa App ficará escutando qualquer requisição referente às mensagens vindas do FCM.
 */
public class FirebaseMessagingEntregadorService extends FirebaseMessagingService {

    private static final int ID_NOTIFICACAO = 1;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                        .setContentTitle("Notificação do Firebase")
                        .setSound(defaultSoundUri)
                        .setSmallIcon(R.mipmap.ic_easygas)
                        .setContentText(remoteMessage.getNotification().getBody());

        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


        mNotificationManager.notify(ID_NOTIFICACAO, mBuilder.build());
    }
}
