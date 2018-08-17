package com.santiago.ws_colorapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.santiago.ws_colorapp.R;

public class ConfiguracionActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextInputEditText tiempoTotal,intentos,tiempoPalabra;
    private Button btnJugar;
    private LinearLayout layoutTiempo,layoutInteno,lineargeneral;
    private RadioButton radioTiempo,radioIntentos;

    public final static int JUEGOINTENTOS=0;
    public final static int JUEGOTIEMPO=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        preferences=getSharedPreferences("Values",MODE_PRIVATE);
        inicializar();

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarJugar();
            }
        });

    }

    private void inicializar() {
        layoutInteno=findViewById(R.id.linearIntentos);
        layoutTiempo=findViewById(R.id.linearTiempo);
        tiempoTotal=findViewById(R.id.txtTiempoConfig);
        intentos=findViewById(R.id.txtIntentosConfig);
        radioIntentos=findViewById(R.id.radioIntentos);
        radioTiempo=findViewById(R.id.radioTiempo);
        tiempoPalabra=findViewById(R.id.txtTiempoPalabraConfig);
        btnJugar=findViewById(R.id.btnJugarConfig);
        lineargeneral = findViewById(R.id.linearGeneral);
    }


    private void validarJugar() {
        int tipoJuego=(radioIntentos.isChecked())?JUEGOINTENTOS:JUEGOTIEMPO;
        if (radioTiempo.isChecked() && validarJuegoTiempo()){
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("tiempoT",tiempoTotal.getText().toString());
            editor.putString("tiempoP",tiempoPalabra.getText().toString());
            editor.apply();

            Intent intent= new Intent(this,JuegoConfigActivity.class);
            intent.putExtra("tiempoT",tiempoTotal.getText().toString());
            intent.putExtra("tipojuego",tipoJuego);
            intent.putExtra("tiempoP",tiempoPalabra.getText().toString());
            startActivity(intent);
        }else if (radioIntentos.isChecked() && validarJuegoIntentos()){
            SharedPreferences.Editor editor=preferences.edit();
            editor.putString("intentos",intentos.getText().toString());
            editor.putString("tiempoP",tiempoPalabra.getText().toString());
            editor.apply();
            Intent intent= new Intent(this,JuegoConfigActivity.class);
            intent.putExtra("tiempoP",tiempoPalabra.getText().toString());
            intent.putExtra("tipojuego",tipoJuego);
            intent.putExtra("intentos",intentos.getText().toString());
            startActivity(intent);
        }
        finish();

    }

    public void validarRadios(View view) {
        switch (view.getId()){
            case R.id.radioIntentos:
                lineargeneral.setVisibility(View.VISIBLE);
                layoutInteno.setVisibility(View.VISIBLE);
                layoutTiempo.setVisibility(View.INVISIBLE);
                layoutTiempo.setVisibility(View.GONE);
                String intentos=preferences.getString("intentos",null);
                String tiempoP=preferences.getString("tiempoP",null);
                if (tiempoP!=null && intentos!=null){
                    this.intentos.setText(intentos);
                    tiempoPalabra.setText(tiempoP);
                }
                break;
            case R.id.radioTiempo:
                lineargeneral.setVisibility(View.VISIBLE);
                layoutTiempo.setVisibility(View.VISIBLE);
                layoutInteno.setVisibility(View.INVISIBLE);
                layoutInteno.setVisibility(View.GONE);
                String tiempoT=preferences.getString("tiempoT",null);
                tiempoP = preferences.getString("tiempoP", null);
                if (null!=tiempoP && null!=tiempoT){
                    tiempoTotal.setText(tiempoT);
                    tiempoPalabra.setText(tiempoP);
                }
                break;
        }
    }

    private boolean validarJuegoIntentos() {
        if (tiempoPalabra.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar un tiempo para la palabra", Toast.LENGTH_SHORT).show();
            return false;
        }else if (intentos.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar la cantidad de intentos", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }

    private boolean validarJuegoTiempo() {
        if (tiempoTotal.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar el tiempo a jugar", Toast.LENGTH_SHORT).show();
            return false;
        }else if (tiempoPalabra.getText().toString().isEmpty()){
            Toast.makeText(this, "Debe ingresar un tiempo para la palabra", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
}
