package com.codigo.rafael.easygas.interfaces;

import com.codigo.rafael.easygas.entities.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shang on 10/10/2017.
 */

public interface ResultsService {

    @GET("{latlng}")
    Call<Results> dadosEndereco(@Path("latlng") String latlng);
}
