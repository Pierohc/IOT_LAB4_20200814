package com.example.tele_futbol.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.tele_futbol.LigasResponseGeneral;
import com.example.tele_futbol.LigasResponsePorPais;

import com.example.tele_futbol.Adapters.LigasAdapter;
import com.example.tele_futbol.ApiClient;
import com.example.tele_futbol.LigasApiService;
import com.example.tele_futbol.R;

import java.util.ArrayList;
import java.util.List;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LigasEuropeas extends Fragment {

    private RecyclerView recyclerView;
    private LigasAdapter adapter;
    private List<Object> itemList; // Usar Object para manejar ambos tipos
    private EditText buscador;
    private Button btnBuscar;

    private LigasApiService ligasApiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ligas_europeas, container, false);

        recyclerView = view.findViewById(R.id.rv);
        buscador = view.findViewById(R.id.buscador);
        btnBuscar = view.findViewById(R.id.btnBuscar);

        itemList = new ArrayList<>();
        adapter = new LigasAdapter(itemList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ligasApiService = ApiClient.getClient().create(LigasApiService.class);

        fetchAllLigas();
        btnBuscar.setOnClickListener(v -> {
            String pais = buscador.getText().toString().trim();
            if (!pais.isEmpty()) {
                fetchLigasPorPais(pais);
            } else {
                fetchAllLigas();
            }
        });

        return view;
    }

    private void fetchAllLigas() {
        Call<LigasResponseGeneral> call = ligasApiService.getAllLigas();
        call.enqueue(new Callback<LigasResponseGeneral>() {
            @Override
            public void onResponse(Call<LigasResponseGeneral> call, Response<LigasResponseGeneral> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.clear();
                    itemList.addAll(response.body().getLeagues());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Error al obtener ligas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigasResponseGeneral> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener ligas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchLigasPorPais(String pais) {
        Call<LigasResponsePorPais> call = ligasApiService.getLigasPorPais(pais);
        call.enqueue(new Callback<LigasResponsePorPais>() {
            @Override
            public void onResponse(Call<LigasResponsePorPais> call, Response<LigasResponsePorPais> response) {
                if (response.isSuccessful() && response.body() != null) {
                    itemList.clear();
                    itemList.addAll(response.body().getCountries());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "No se encontraron ligas para el país: " + pais, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LigasResponsePorPais> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener ligas para el país", Toast.LENGTH_SHORT).show();
            }
        });
    }
}