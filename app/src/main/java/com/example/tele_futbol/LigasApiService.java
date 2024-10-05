package com.example.tele_futbol;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LigasApiService {

    @GET("all_leagues.php")
    Call<LigasResponseGeneral> getAllLigas(); // Para el primer GET

    @GET("search_all_leagues.php")
    Call<LigasResponsePorPais> getLigasPorPais(@Query("c") String pais); // Para el segundo GET
}
