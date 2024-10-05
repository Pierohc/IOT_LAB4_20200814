package com.example.tele_futbol;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tele_futbol.Fragments.LigasEuropeas;
import com.example.tele_futbol.Fragments.PosicionesLiga;
import com.example.tele_futbol.Fragments.ResultadosPartidos;


public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        Button ligas = findViewById(R.id.ligas);
        Button posiciones = findViewById(R.id.pos);
        Button resultados = findViewById(R.id.results);

        Fragment ligasFragment = new LigasEuropeas();
        Fragment posicionesFragment = new PosicionesLiga();
        Fragment resultadosFragment = new ResultadosPartidos();

        replaceFragment(ligasFragment);

        ligas.setOnClickListener(v -> replaceFragment(ligasFragment));
        posiciones.setOnClickListener(v -> replaceFragment(posicionesFragment));
        resultados.setOnClickListener(v -> replaceFragment(resultadosFragment));
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

}
