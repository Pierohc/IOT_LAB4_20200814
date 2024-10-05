package com.example.tele_futbol;

import com.example.tele_futbol.Models.Equipo;

import java.util.List;

public class PosicionesResponse {
    private List<Equipo> table; // Asumiendo que la respuesta tiene un campo "table"

    public List<Equipo> getTable() {
        return table;
    }
}
