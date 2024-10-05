package com.example.tele_futbol.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_futbol.Adapters.PosicionesAdapter;
import com.example.tele_futbol.ApiClient;
import com.example.tele_futbol.Models.Equipo;
import com.example.tele_futbol.PosicionesApiService;
import com.example.tele_futbol.PosicionesResponse;
import com.example.tele_futbol.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosicionesLiga extends Fragment {

    private RecyclerView recyclerView;
    private PosicionesAdapter adapter;
    private List<Equipo> equiposList;
    private EditText idLigaEditText, temporadaEditText;
    private Button btnBuscar;

    private PosicionesApiService posicionesApiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_posiciones_liga, container, false);

        recyclerView = view.findViewById(R.id.rv);
        idLigaEditText = view.findViewById(R.id.idLiga);
        temporadaEditText = view.findViewById(R.id.temporada);
        btnBuscar = view.findViewById(R.id.btnBuscar);

        equiposList = new ArrayList<>();
        adapter = new PosicionesAdapter(equiposList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        posicionesApiService = ApiClient.getClient().create(PosicionesApiService.class);

        btnBuscar.setOnClickListener(v -> {
            String idLiga = idLigaEditText.getText().toString().trim();
            String temporada = temporadaEditText.getText().toString().trim();

            if (!idLiga.isEmpty() && !temporada.isEmpty()) {
                fetchPosiciones(idLiga, temporada);
            } else {
                Toast.makeText(getContext(), "Por favor ingresa el ID de la liga y la temporada", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fetchPosiciones(String idLiga, String temporada) {
        Call<PosicionesResponse> call = posicionesApiService.getPosiciones(idLiga, temporada);
        call.enqueue(new Callback<PosicionesResponse>() {
            @Override
            public void onResponse(Call<PosicionesResponse> call, Response<PosicionesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    equiposList.clear();
                    equiposList.addAll(response.body().getTable());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se encontraron posiciones para la liga y temporada especificadas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PosicionesResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener posiciones", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
