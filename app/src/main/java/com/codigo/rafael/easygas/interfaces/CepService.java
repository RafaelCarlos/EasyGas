package com.codigo.rafael.easygas.interfaces;



import com.codigo.rafael.easygas.entities.Cep;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by shang on 09/08/2017.
 */

public interface CepService {
    @GET("ws/{cep}/json")
    Call<Cep> dados(@Path("cep") String cep);
}
