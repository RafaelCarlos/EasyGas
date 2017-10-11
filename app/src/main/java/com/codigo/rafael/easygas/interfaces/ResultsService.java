package com.codigo.rafael.easygas.interfaces;

import com.codigo.rafael.easygas.entities.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by shang on 10/10/2017.
 */

public interface ResultsService {

    @GET("json")
    Call<Results> dadosEndereco(@Query("latlng") String latlng);
}
