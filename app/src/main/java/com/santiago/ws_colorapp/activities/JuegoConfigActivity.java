package com.santiago.ws_colorapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.santiago.ws_colorapp.R;
import com.santiago.ws_colorapp.datos.Utils;

import java.util.Locale;
import java.util.Random;

public class JuegoConfigActivity extends AppCompatActivity {

    private TextView tiempo,movimientos,reaccion,intentos,intentosView,palabra,tiempoView;
    private FloatingActionButton fab1,fab2,fab3,fab4;
    private long tiempoTotal,tiempoPalabra;
    private int intentosC,correctas=0,incorrectas=0,mil=1000,totalPalabras=0,posicionPalabra;
    private Random random= new Random();
    private boolean seleccion=false;
    private float porReaccion;
    String colores[]={"AMARILLO","AZUL","VERDE","ROJO"};
    int bColor1=0,bColor2=0,bColor3=0,bColor4=0;
    int color=0, count=0;
    boolean estado=true;
    Button pause, pauseIntentos;
    private CountDownTimer contador, contador_intentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_config);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        incializar();
        recogerDatos();
        }

    private void recogerDatos() {
        Bundle bundle=getIntent().getExtras();
        if (bundle!= null){
            int tipoJuego=bundle.getInt("tipojuego");
            switch (tipoJuego){
                case ConfiguracionActivity.JUEGOINTENTOS:
                    tiempo.setVisibility(View.GONE);
                    tiempoView.setVisibility(View.GONE);
                    intentosC= Integer.parseInt(bundle.getString("intentos"))+1;
                    tiempoPalabra= Long.parseLong(bundle.getString("tiempoP"))*1000;
                    playCIntentos();
                    break;
                case ConfiguracionActivity.JUEGOTIEMPO:
                    intentos.setVisibility(View.GONE);
                    intentosView.setVisibility(View.GONE);
                    tiempoTotal= Long.parseLong(bundle.getString("tiempoT"))*1000;
                    tiempoPalabra= Long.parseLong(bundle.getString("tiempoP"))*1000;
                    playC();
                    break;
            }
        }
    }


    private void iniciarJuego() {
        pauseIntentos.setVisibility(View.GONE);
        pause.setVisibility(View.VISIBLE);
        contador =new CountDownTimer(tiempoTotal,mil) {
            @Override
            public void onTick(long l) {
                int time= (int) (l/mil);
                tiempoTotal=l;
                actualizarTextoTiempo();
                tiempo.setText(time+"''");
                if((JuegoConfigActivity.this.isFinishing())){
                    finish();
                    cancel();
                }else if (incorrectas==3 ){
                    totalPalabras    = correctas + incorrectas;
                    onFinish();
                    cancel();
                }
                if (count ==4){
                    pause.setBackgroundResource(R.drawable.play_desactivado);
                    pause.setEnabled(false);
                }
            }

            @Override
            public void onFinish() {
                tiempo.setText("0''");
                desabilitarBotones();
                final String message="Correctas:"+correctas+"\n"+
                        "Incorrectas: "+ incorrectas+"\n"+
                        "Reccion:"+ reaccion.getText().toString();
                Utils.alertDialog(JuegoConfigActivity.this,message);
            }
        }.start();

    }
    private void cambiarPalabra() {
        habilitarBotones();
        totalPalabras++;
        movimientos.setText(totalPalabras+"");
        intentos.setText(intentosC+"");
        posicionPalabra=random.nextInt(4);
        palabra.setText(colores[posicionPalabra]);

        new CountDownTimer(tiempoPalabra, mil) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if (!tiempo.getText().toString().equalsIgnoreCase("0''")){
                    if (seleccion==false){
                        porReaccion=((float) correctas/(float)totalPalabras)*100;
                        reaccion.setText((int) porReaccion+"%");
                        incorrectas++;
                    }
                    cambiarColorPalabra();
                    cambiarPalabra();
                    cambiarColorBotones();
                }
            }
        }.start();

    }
    private void cambiarColorBotones() {
        seleccion=false;
        boolean btn1=false,btn2=false,btn3=false,btn4=false;
        int color1=0,color2=0,color3=0,color4=0;
        while ( btn1==false || btn2==false || btn3==false || btn4==false){
            int azar=random.nextInt(4);
            switch (azar){
                case 0:
                    if (btn1==false){
                        if (color1==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor1=0;
                            btn1=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn1==false){
                        if (color2==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor1=1;
                            btn1=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn1==false){
                        if (color3==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor1=2;
                            btn1=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn1==false){
                        if (color4==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor1=3;
                            btn1=true;
                            color4=1;
                            break;
                        }
                    }
                case 1:
                    if (btn2==false){
                        if (color1==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor2=0;
                            btn2=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn2==false){
                        if (color2==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor2=1;
                            btn2=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn2==false){
                        if (color3==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor2=2;
                            btn2=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn2==false){
                        if (color4==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor2=3;
                            btn2=true;
                            color4=1;
                            break;
                        }
                    }
                case 2:
                    if (btn3==false){
                        if (color1==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor3=0;
                            btn3=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn3==false){
                        if (color2==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor3=1;
                            btn3=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn3==false){
                        if (color3==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor3=2;
                            btn3=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn3==false){
                        if (color4==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor3=3;
                            btn3=true;
                            color4=1;
                            break;
                        }
                    }
                case 3:
                    if (btn4==false){
                        if (color1==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor4=0;
                            btn4=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn4==false){
                        if (color2==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor4=1;
                            btn4=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn4==false){
                        if (color3==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor4=2;
                            btn4=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn4==false){
                        if (color4==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor4=3;
                            btn4=true;
                            color4=1;
                            break;
                        }
                    }
            }
        }

    }
    private void cambiarColorPalabra() {
        color=random.nextInt(4);
        switch (color){
            case 0:palabra.setTextColor(Color.parseColor("#b7b716"));
                break;
            case 1:palabra.setTextColor(Color.parseColor("#0a7300"));
                break;
            case 2:palabra.setTextColor(Color.parseColor("#090f89"));
                break;
            case 3:palabra.setTextColor(Color.parseColor("#c71f1e"));
                break;
        }
        new CountDownTimer(tiempoPalabra, mil) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if (!tiempo.getText().toString().equalsIgnoreCase("0''")){
                    cambiarColorPalabra();
                }

            }
        }.start();

    }

    public void pausarJuegoC(View view) {
        count++;
        if (estado){
            playC();
        }else {
            pausarC();
            palabra.setEnabled(false);
            desabilitarBotones();
        }
    }
    private void playC() {
        iniciarJuego();
        cambiarColorPalabra();
        cambiarColorBotones();
        cambiarPalabra();
        estado=false;
        pause.setBackgroundResource(R.drawable.pause);

    }
    private void pausarC() {
        totalPalabras--;
        tiempo.setText("0''");
        contador.cancel();
        estado=true;
        pause.setBackgroundResource(R.drawable.play);
    }
    private void actualizarTextoTiempo() {
        int t= (int) (tiempoTotal/mil);
        String tt= String.format( Locale.getDefault(),"%02d", t);
        tiempo.setText(tt);

    }



    private void iniciarJuegoIntentos() {
        pause.setVisibility(View.GONE);
        pauseIntentos.setVisibility(View.VISIBLE);

        if (incorrectas==3 ){
            tiempoPalabra=0;
            final String message="Correctas:"+correctas+"\n"+
                    "Incorrectas: "+ incorrectas+"\n"+
                    "Reccion:"+ reaccion.getText().toString();
            Utils.alertDialog(JuegoConfigActivity.this,message);
        }

        if (count == 4){
            pauseIntentos.setBackgroundResource(R.drawable.play_desactivado);
            pauseIntentos.setEnabled(false);
        }
        cambiarColorPalabraIntentos();
        cambiarColorBotonesIntentos();
        cambiarPalabraIntentos();


    }
    private void cambiarPalabraIntentos() {
        habilitarBotones();
        totalPalabras++;
        intentosC--;
        movimientos.setText(totalPalabras+"");
        intentos.setText(intentosC+"");
        posicionPalabra=random.nextInt(4);
        palabra.setText(colores[posicionPalabra]);

        contador_intentos = new CountDownTimer(tiempoPalabra, mil) {
            @Override
            public void onTick(long l) {
                if (intentosC==0){
                    tiempoPalabra=0;
                    final String message="Correctas:"+correctas+"\n"+
                            "Incorrectas: "+ incorrectas+"\n"+
                            "Reccion:"+ reaccion.getText().toString();
                    Utils.alertDialog(JuegoConfigActivity.this,message);
                }

            }

            @Override
            public void onFinish() {
                if (!intentos.getText().toString().equalsIgnoreCase("0")){
                    if (seleccion==false){
                        porReaccion=((float) correctas/(float)totalPalabras)*100;
                        reaccion.setText((int) porReaccion+"%");
                        incorrectas++;
                    }
                    iniciarJuegoIntentos();
                }
            }
        }.start();
    }
    private void cambiarColorBotonesIntentos() {
        seleccion=false;
        boolean btn1=false,btn2=false,btn3=false,btn4=false;
        int color1=0,color2=0,color3=0,color4=0;
        while ( btn1==false || btn2==false || btn3==false || btn4==false){
            int azar=random.nextInt(4);
            switch (azar){
                case 0:
                    if (btn1==false){
                        if (color1==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor1=0;
                            btn1=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn1==false){
                        if (color2==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor1=1;
                            btn1=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn1==false){
                        if (color3==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor1=2;
                            btn1=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn1==false){
                        if (color4==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor1=3;
                            btn1=true;
                            color4=1;
                            break;
                        }
                    }
                case 1:
                    if (btn2==false){
                        if (color1==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor2=0;
                            btn2=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn2==false){
                        if (color2==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor2=1;
                            btn2=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn2==false){
                        if (color3==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor2=2;
                            btn2=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn2==false){
                        if (color4==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor2=3;
                            btn2=true;
                            color4=1;
                            break;
                        }
                    }
                case 2:
                    if (btn3==false){
                        if (color1==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor3=0;
                            btn3=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn3==false){
                        if (color2==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor3=1;
                            btn3=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn3==false){
                        if (color3==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor3=2;
                            btn3=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn3==false){
                        if (color4==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor3=3;
                            btn3=true;
                            color4=1;
                            break;
                        }
                    }
                case 3:
                    if (btn4==false){
                        if (color1==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor4=0;
                            btn4=true;
                            color1=1;
                            break;
                        }
                    }
                    if (btn4==false){
                        if (color2==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor4=1;
                            btn4=true;
                            color2=1;
                            break;
                        }
                    }
                    if (btn4==false){
                        if (color3==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor4=2;
                            btn4=true;
                            color3=1;
                            break;
                        }
                    }
                    if (btn4==false){
                        if (color4==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor4=3;
                            btn4=true;
                            color4=1;
                            break;
                        }
                    }
            }
        }

    }
    private void cambiarColorPalabraIntentos() {
        color=random.nextInt(4);
        switch (color){
            case 0:palabra.setTextColor(Color.parseColor("#b7b716"));
                break;
            case 1:palabra.setTextColor(Color.parseColor("#0a7300"));
                break;
            case 2:palabra.setTextColor(Color.parseColor("#090f89"));
                break;
            case 3:palabra.setTextColor(Color.parseColor("#c71f1e"));
                break;
        }
    }

    public void pausarJuegoCIntentos(View view) {
        count++;
        if (estado){
            playCIntentos();
        }else {
            pausarCIntentos();
        }
    }
    private void playCIntentos() {
        iniciarJuegoIntentos();
        estado=false;
        pauseIntentos.setBackgroundResource(R.drawable.pause);

    }
    private void pausarCIntentos() {
        palabra.setEnabled(false);
        desabilitarBotones();
        totalPalabras--;
        intentosC++;
        contador_intentos.cancel();
        estado=true;
        pauseIntentos.setBackgroundResource(R.drawable.play);
    }



    private void incializar() {
        tiempo=findViewById(R.id.txtTiempoJuegoConfig);
        movimientos=findViewById(R.id.txtmovimientosConfig);
        reaccion=findViewById(R.id.txtReaccionConfig);
        intentos=findViewById(R.id.txtIntentosJuegoConfig);
        intentosView=findViewById(R.id.txtintentosView);
        palabra=findViewById(R.id.txtPalabraConfig);
        tiempoView = findViewById(R.id.tiempoView);

        fab1=findViewById(R.id.fab1Config);
        fab2=findViewById(R.id.fab2Config);
        fab3=findViewById(R.id.fab3Config);
        fab4=findViewById(R.id.fab4Config);
        pause= findViewById(R.id.button_pausarC);
        pauseIntentos= findViewById(R.id.button_pausarCIntentos);
    }
    private void desabilitarBotones() {
        fab1.setEnabled(false);
        fab2.setEnabled(false);
        fab3.setEnabled(false);
        fab4.setEnabled(false);
    }
    private void habilitarBotones() {
        fab1.setEnabled(true);
        fab2.setEnabled(true);
        fab3.setEnabled(true);
        fab4.setEnabled(true);
    }
    public void verificarRespuestaConfig(View view) {
        seleccion=true;
        desabilitarBotones();
        if (view.getId()==fab1.getId()){
            if (color==bColor1){
                correctas++;
            }
        }
        if (view.getId()==fab2.getId()){
            if (color==bColor2){
                correctas++;
            }
        }
        if (view.getId()==fab3.getId()){
            if (color==bColor3){
                correctas++;
            }
        }
        if (view.getId()==fab4.getId()){
            if (color==bColor4){
                correctas++;
            }
        }

        porReaccion=((float)correctas/(float)totalPalabras)*100;
        reaccion.setText((int) porReaccion+"%");
        incorrectas=totalPalabras-correctas;
    } //cada onclick
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
