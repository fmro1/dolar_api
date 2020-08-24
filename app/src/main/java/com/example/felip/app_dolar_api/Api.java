package com.example.felip.app_dolar_api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by felip on 28/05/2020.
 */
public interface Api {
    String BASE_URL = "https://free.currconv.com/api/v7/";

    @GET("convert?q=USD_BRL&compact=ultra&apiKey=2d0ad47e4258ed1e8290")

    Call<Moeda> converter();
}
