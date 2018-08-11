package com.santiago.ws_colorapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.santiago.ws_colorapp.R;
import com.santiago.ws_colorapp.modelo.Juego;

import java.util.ArrayList;

public class AdapterPuntajes extends RecyclerView.Adapter<AdapterPuntajes.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<Juego>juegos;
    private onItemClickListener listener;

    public AdapterPuntajes(Context context, int layout, ArrayList<Juego> juegos, onItemClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.juegos = juegos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.Bind(juegos.get(position),listener);

    }

    @Override
    public int getItemCount() {
        return juegos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView puntos;
        public ViewHolder(View itemView) {
            super(itemView);
            puntos=itemView.findViewById(R.id.txtPuntos);
        }

        public void Bind(final Juego juego, final onItemClickListener listener){
            int puntaje = (int) juego.getReaccion();
            puntos.setText(puntaje+"%");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(juego,getAdapterPosition());
                }
            });

        }

    }

    public interface onItemClickListener{
        void onItemClick(Juego juego,int position);
    }
}
