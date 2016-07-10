package com.example.alink.huerto;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nicolas on 10-07-16.
 */
public class RecyclerViewAdapterOpcion extends RecyclerView.Adapter<RecyclerViewAdapterOpcion.OpcionViewHolder> {

    private static List<OpcionCultivo> items;
    private static Cultivo cultivo;

    public static class OpcionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView opcionTitulo, opcionDescripcion;
        public int position;

        public OpcionViewHolder(View v) {
            super(v);
            opcionTitulo = (TextView) v.findViewById(R.id.opcion_cultivo_titulo);
            opcionDescripcion = (TextView) v.findViewById(R.id.opcion_cultivo_detalle);

            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OpcionCultivo opcionCultivo = items.get(position);
            Intent i = null;
            switch (opcionCultivo.getNombre()){
                case "Plantas":
                    i = new Intent(v.getContext(), DetalleCultivo.class);
                    i.putExtra("opcion", opcionCultivo);
                    i.putExtra("idCultivo", String.valueOf(cultivo.getIdCultivo()));
                    break;
                case "Recetas":
                    i = new Intent(v.getContext(), null);
                    i.putExtra("opcion", opcionCultivo);
                    i.putExtra("cultivo", cultivo);
                    break;
            }
            v.getContext().startActivity(i);
        }
    }

    public RecyclerViewAdapterOpcion(List<OpcionCultivo> items, Cultivo cultivo) {
        this.items = items;
        this.cultivo = cultivo;
    }

    @Override
    public OpcionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.opcion_cultivo_card, parent, false);

        OpcionViewHolder vh = new OpcionViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(OpcionViewHolder holder, int position) {

        holder.opcionTitulo.setText(items.get(position).getNombre());
        holder.opcionDescripcion.setText(items.get(position).getDescripcion());
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
