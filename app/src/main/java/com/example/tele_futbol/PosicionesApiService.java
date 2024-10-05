package com.example.tele_futbol;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PosicionesApiService {

    @GET("lookuptable.php")
    Call<PosicionesResponse> getPosiciones(@Query("l") String idLiga, @Query("s") String temporada);
}
