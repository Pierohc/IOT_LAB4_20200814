package com.example.tele_futbol.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_futbol.Encuentro;
import com.example.tele_futbol.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultadosAdapter extends RecyclerView.Adapter<ResultadosAdapter.EncuentroViewHolder> {

    private List<Encuentro> encuentrosList;
    private Context context;

    public ResultadosAdapter(List<Encuentro> encuentrosList, Context context) {
        this.encuentrosList = encuentrosList;
        this.context = context;
    }

    @NonNull
    @Override
    public EncuentroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_encuentro, parent, false);
        return new EncuentroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EncuentroViewHolder holder, int position) {
        Encuentro encuentro = encuentrosList.get(position);
        holder.nombreCompetencia.setText(encuentro.getStrLeague());
        holder.ronda.setText("Ronda: " + encuentro.getIntRound());
        holder.equipoLocal.setText(encuentro.getStrHomeTeam());
        holder.equipoVisitante.setText(encuentro.getStrAwayTeam());
        holder.resultado.setText(encuentro.getIntHomeScore() + " - " + encuentro.getIntAwayScore());
        holder.fecha.setText(encuentro.getDateEvent());
        holder.espectadores.setText("Espectadores: " + encuentro.getIntSpectators());

        Picasso.get()
                .load(encuentro.getStrLogo())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(holder.logoCompetencia);
    }

    @Override
    public int getItemCount() {
        return encuentrosList.size();
    }

    public class EncuentroViewHolder extends RecyclerView.ViewHolder {
        TextView nombreCompetencia, ronda, equipoLocal, equipoVisitante, resultado, fecha, espectadores;
        ImageView logoCompetencia;

        public EncuentroViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreCompetencia = itemView.findViewById(R.id.nombre_competencia);
            ronda = itemView.findViewById(R.id.ronda);
            equipoLocal = itemView.findViewById(R.id.equipo_local);
            equipoVisitante = itemView.findViewById(R.id.equipo_visitante);
            resultado = itemView.findViewById(R.id.resultado);
            fecha = itemView.findViewById(R.id.fecha);
            espectadores = itemView.findViewById(R.id.espectadores);
            logoCompetencia = itemView.findViewById(R.id.logo_competencia);
        }
    }
}
