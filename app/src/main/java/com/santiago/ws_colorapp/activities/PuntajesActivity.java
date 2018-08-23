package com.santiago.ws_colorapp.activities;

import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.santiago.ws_colorapp.R;
import com.santiago.ws_colorapp.adapter.AdapterPuntajes;
import com.santiago.ws_colorapp.datos.DatosBD;
import com.santiago.ws_colorapp.datos.Utils;
import com.santiago.ws_colorapp.modelo.Juego;

import java.util.ArrayList;

public class PuntajesActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdapterPuntajes adpater;
    private ArrayList<Juego> juegos;
    private Juego juego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntajes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        try {
            recyclerView=findViewById(R.id.recycler);
            juegos=getPuntajes();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adpater=new AdapterPuntajes(this, R.layout.content_card_view, juegos, new AdapterPuntajes.onItemClickListener() {
                @Override
                public void onItemClick(Juego juego, int position) {

                }
            });
            recyclerView.setAdapter(adpater);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private ArrayList<Juego> getPuntajes() {
        DatosBD bd= new DatosBD(this);
        Cursor cursor=bd.listarPuntajes();
        juegos= new ArrayList<>();

        do {
            juego= new Juego();
            juego.setReaccion(cursor.getInt(cursor.getColumnIndex(Utils.CAMPO_REACCION)));
            juegos.add(juego);
        }while (cursor.moveToNext());
        return juegos;
    }
}
