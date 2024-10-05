package com.example.tele_futbol;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ResultadosApiService {

    @GET("eventsround.php")
    Call<ResultadosResponse> getResultados(@Query("id") String idLiga, @Query("r") String ronda, @Query("s") String temporada);
}

