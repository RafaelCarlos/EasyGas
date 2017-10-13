package com.codigo.rafael.easygas.interfaces;

import com.codigo.rafael.easygas.entities.convertersendere.Pojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rafael Carlos Oliveira on 10/10/2017.
 */

public interface ResultsService {

    @GET("json")
    Call<Pojo> dadosEndereco(@Query("latlng") String latlng, @Query("key") String key);
}
