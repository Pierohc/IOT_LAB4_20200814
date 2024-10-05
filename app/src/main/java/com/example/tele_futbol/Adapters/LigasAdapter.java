package com.example.tele_futbol.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_futbol.Models.Countries;
import com.example.tele_futbol.Models.Liga;
import com.example.tele_futbol.R;

import java.util.List;

public class LigasAdapter extends RecyclerView.Adapter<LigasAdapter.LigaViewHolder> {

    private List<Object> itemList;
    private Context context;

    public LigasAdapter(List<Object> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public LigaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liga, parent, false);
        return new LigaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigaViewHolder holder, int position) {
        Object item = itemList.get(position);

        if (item instanceof Liga) {
            Liga liga = (Liga) item;
            holder.nombre.setText(liga.getStrLeague());
            holder.id.setText(liga.getIdLeague());
            holder.deporte.setText(liga.getStrSport());
            holder.nombreAlternativo.setText(liga.getStrLeagueAlternate());
        } else if (item instanceof Countries) {
            Countries country = (Countries) item;
            holder.nombre.setText(country.getStrLeague());
            holder.id.setText(country.getIdLeague());
            holder.deporte.setText(country.getStrSport());
            holder.nombreAlternativo.setText(country.getStrLeagueAlternate());
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class LigaViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, id, deporte, nombreAlternativo;

        public LigaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            id = itemView.findViewById(R.id.id);
            deporte = itemView.findViewById(R.id.deporte);
            nombreAlternativo = itemView.findViewById(R.id.nombre_alternativo);
        }
    }
}


