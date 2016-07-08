package com.example.alink.huerto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Luis on 07-07-2016.
 */
public class ReciclerViewAdapter extends RecyclerView.Adapter<ReciclerViewAdapter.PlantaViewHolder> {
        private static List<Planta> items;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class PlantaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public TextView nombre;
            public TextView nombreCientifico;
            public ImageView imgViewIcon;

            //public IMyViewHolderClicks mListener;
            public int position;
            public String idPlanta;

            public PlantaViewHolder(View v/*, IMyViewHolderClicks listener*/) {
                super(v);
               //mListener = listener;
                nombre = (TextView) v.findViewById(R.id.detNombre);
                nombreCientifico = (TextView) v.findViewById(R.id.textViewNombreCientifico);
                imgViewIcon = (ImageView) v.findViewById(R.id.imagen);

                imgViewIcon.setOnClickListener(this);
                v.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                /*if (v instanceof ImageView){
                    //mListener.onTomato((ImageView)v);
                    Intent intent = new Intent(v.getContext(), DetallePlantaActivity.class);
                    intent.putExtra("idPlanta", idPlanta);

                    v.getContext().startActivity(intent);

                } else {
                    //mListener.onPotato(v);
                }*/

                Planta planta = items.get(position);
                Intent i = new Intent(v.getContext(), DetallePlantaActivity.class);
                i.putExtra("planta", planta);
                v.getContext().startActivity(i);
            }

            public static interface IMyViewHolderClicks {
                public void onPotato(View caller);
                public void onTomato(ImageView callerImage);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public ReciclerViewAdapter(List<Planta> items) {
            this.items = items;
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return items.size();
        }


        // Create new views (invoked by the layout manager)
        @Override
        public PlantaViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.planta_card, parent, false);

            /*PlantaViewHolder vh = new PlantaViewHolder(v,
                    new PlantaViewHolder.IMyViewHolderClicks() {
                public void onPotato(View caller) { Log.d("VEGETABLES", "Poh-tah-tos"); };
                public void onTomato(ImageView callerImage) { Log.d("VEGETABLES", "To-m8-tohs"); }
            });*/


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
            String aux =items.get(position).getNombre();
            holder.imgViewIcon.setImageResource(R.drawable.ajo);
            holder.position = position;
            holder.idPlanta = items.get(position).getIdPlanta();

        }



}
