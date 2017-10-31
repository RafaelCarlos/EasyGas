package com.codigo.rafael.easygas.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Rafael Carlos Oliveira on 31/10/2017.
 */

/**
 * Essa classe será responsável em receber um token único que o Firebase gera para vincular ao dispositivo,
 * pois, a partir desse token, o Firebase consegue enviar as mensagens para os dispositivos. Para pegarmos esse token,
 * podemos sobrescrever o método onTokenRefresh():
 */
public class FirebaseInstanceEntregadorService extends FirebaseInstanceIdService {

    /**
     * Esse método pode ser chamado a partir das seguintes ações.
     * Deletando o Instance ID.
     * Restaurando a App em um novo dispositivo.
     * Usuário deletando ou reinstalando a App.
     * Usuário limpando os dados da App.
     */
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Token App", token);
    }
}
