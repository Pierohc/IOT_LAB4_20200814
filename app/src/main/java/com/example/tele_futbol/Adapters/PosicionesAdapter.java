package com.example.tele_futbol.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_futbol.Models.Equipo;
import com.example.tele_futbol.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PosicionesAdapter extends RecyclerView.Adapter<PosicionesAdapter.EquipoViewHolder> {

    private List<Equipo> equiposList;
    private Context context;

    public PosicionesAdapter(List<Equipo> equiposList, Context context) {
        this.equiposList = equiposList;
        this.context = context;
    }

    @NonNull
    @Override
    public EquipoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_equipo, parent, false);
        return new EquipoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EquipoViewHolder holder, int position) {
        Equipo equipo = equiposList.get(position);
        holder.nombre.setText(equipo.getStrTeam());
        holder.ranking.setText(equipo.getIntRank());
        holder.victoriasEmpatesDerrotas.setText(
                equipo.getIntWins() + "/" + equipo.getIntDraws() + "/" + equipo.getIntLosses()
        );
        holder.goles.setText(
                equipo.getIntGoalsFor() + "/" + equipo.getIntGoalsAgainst() + "/" + equipo.getIntGoalDifference()
        );

        Picasso.get()
                .load(equipo.getStrTeamBadge())
                .into(holder.badge);
    }

    @Override
    public int getItemCount() {
        return equiposList.size();
    }

    public class EquipoViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, ranking, victoriasEmpatesDerrotas, goles;
        ImageView badge;

        public EquipoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            ranking = itemView.findViewById(R.id.ranking);
            victoriasEmpatesDerrotas = itemView.findViewById(R.id.victorias_empates_derrotas);
            goles = itemView.findViewById(R.id.goles);
            badge = itemView.findViewById(R.id.badge); // ImageView para el badge
        }
    }
}

