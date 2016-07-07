package com.example.alink.huerto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Luis on 07-07-2016.
 */
public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.PlantaViewHolder> {
        private List<Planta> items;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class PlantaViewHolder extends RecyclerView.ViewHolder {
            public TextView nombre;
            public TextView nombreCientifico;

            public PlantaViewHolder(View v) {
                super(v);
                nombre = (TextView) v.findViewById(R.id.textViewNombre);
                nombreCientifico = (TextView) v.findViewById(R.id.textViewNombreCientifico);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ReciclerViewAdapter(List<Planta> items) {
            this.items = items;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public PlantaViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.planta_card, parent, false);

            PlantaViewHolder vh = new PlantaViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(PlantaViewHolder holder, int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.nombre.setText(items.get(position).getNombre());
            holder.nombreCientifico.setText(items.get(position).getNombreCientifico());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return items.size();
        }

}
