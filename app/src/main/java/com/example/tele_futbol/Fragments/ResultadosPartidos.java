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

import com.example.tele_futbol.Adapters.ResultadosAdapter;
import com.example.tele_futbol.ApiClient;
import com.example.tele_futbol.Encuentro;
import com.example.tele_futbol.R;
import com.example.tele_futbol.ResultadosApiService;
import com.example.tele_futbol.ResultadosResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultadosPartidos extends Fragment {

    private RecyclerView recyclerView;
    private ResultadosAdapter adapter;
    private List<Encuentro> encuentrosList;
    private EditText idLigaEditText, temporadaEditText, rondaEditText;
    private Button btnBuscar;

    private ResultadosApiService resultadosApiService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_resultados_partidos, container, false);

        recyclerView = view.findViewById(R.id.rv);
        idLigaEditText = view.findViewById(R.id.idLiga);
        temporadaEditText = view.findViewById(R.id.temporada);
        rondaEditText = view.findViewById(R.id.ronda);
        btnBuscar = view.findViewById(R.id.btnBuscar);

        encuentrosList = new ArrayList<>();
        adapter = new ResultadosAdapter(encuentrosList, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        resultadosApiService = ApiClient.getClient().create(ResultadosApiService.class);

        btnBuscar.setOnClickListener(v -> {
            String idLiga = idLigaEditText.getText().toString().trim();
            String temporada = temporadaEditText.getText().toString().trim();
            String ronda = rondaEditText.getText().toString().trim();

            if (!idLiga.isEmpty() && !temporada.isEmpty() && !ronda.isEmpty()) {
                fetchResultados(idLiga, temporada, ronda);
            } else {
                Toast.makeText(getContext(), "Por favor ingresa el ID de la liga, la temporada y la ronda", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fetchResultados(String idLiga, String temporada, String ronda) {
        Call<ResultadosResponse> call = resultadosApiService.getResultados(idLiga, ronda, temporada);
        call.enqueue(new Callback<ResultadosResponse>() {
            @Override
            public void onResponse(Call<ResultadosResponse> call, Response<ResultadosResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Encuentro> nuevosEncuentros = response.body().getEvents();
                    if (nuevosEncuentros != null && !nuevosEncuentros.isEmpty()) {
                        encuentrosList.addAll(nuevosEncuentros);
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "No se encontraron resultados para los criterios ingresados", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error al obtener los resultados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultadosResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red al obtener los resultados", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
