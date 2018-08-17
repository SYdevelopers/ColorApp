package com.santiago.ws_colorapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.santiago.ws_colorapp.R;
import com.santiago.ws_colorapp.datos.DatosBD;
import com.santiago.ws_colorapp.modelo.Juego;

import java.util.Locale;
import java.util.Random;

public class JuegoActivity extends AppCompatActivity {
    Button pause;
    TextView tiempo,movimientos,reaccion,palabra,correctas1, incorrectas1;
    FloatingActionButton fab1,fab2,fab3,fab4;
    long tiempoTotal=30000,tiempoPalabra=3000;
    int mil=1000,incorrectas=0,correctas=0,totalPalabaras=0,posicionPalabra;
    String colores[]={"AMARILLO","ROJO","AZUL","VERDE"};
    Random random= new Random();
    boolean seleccion=false;
    float porReaccion, reacc;
    boolean estado=true;
    int movi, correc, incorec;
    private CountDownTimer contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inicializar();
        reaccion.setText("0%");

    }

    private void cambiarPalabra() {
        habilitarBotones();
        totalPalabaras++;
        correctas1.setText(correctas+"");
        incorrectas1.setText(incorrectas+"");
        movimientos.setText(totalPalabaras+"");
        posicionPalabra=random.nextInt(4);
        palabra.setText(colores[posicionPalabra]);

        new CountDownTimer(tiempoPalabra,mil) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if (!tiempo.getText().toString().equalsIgnoreCase("0''")){
                    if (seleccion==false){
                        porReaccion=((float) correctas/(float) totalPalabaras)*100;
                        reaccion.setText((int) porReaccion+"%");
                        incorrectas++;
                    }

                    cambiarColorPalabra();
                    cambiarPalabra();
                    cambiarColorBoton();
                }
            }
        }.start();

    }

    int bColor1=0,bColor2=0,bColor3=0,bColor4=0;
    private void cambiarColorBoton() {
        seleccion=false;
        boolean btn1=false,btn2=false,btn3=false,btn4=false;
        int color1=0,color2=0,color3=0,color4=0;
        while (btn1==false || btn2==false || btn3==false || btn4==false){
            int azar=random.nextInt(4);
            switch (azar){
                case 0:
                    if (btn1==false){
                        if (color1==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor1=0;
                            color1=1;
                            btn1=true;
                            break;
                        }
                        if (color2==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor1=1;
                            color2=1;
                            btn1=true;
                            break;
                        }
                        if (color3==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor1=2;
                            color3=1;
                            btn1=true;
                            break;
                        }
                        if (color4==0){
                            fab1.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor1=3;
                            color4=1;
                            btn1=true;
                            break;
                        }
                    }
                case 1:
                    if (btn2==false){
                        if (color1==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor2=0;
                            color1=1;
                            btn2=true;
                            break;
                        }
                        if (color2==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor2=1;
                            color2=1;
                            btn2=true;
                            break;
                        }
                        if (color3==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor2=2;
                            color3=1;
                            btn2=true;
                            break;
                        }
                        if (color4==0){
                            fab2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor2=3;
                            color4=1;
                            btn2=true;
                            break;
                        }
                    }
                case 2:
                    if (btn3==false){
                        if (color1==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor3=0;
                            color1=1;
                            btn3=true;
                            break;
                        }
                        if (color2==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor3=1;
                            color2=1;
                            btn3=true;
                            break;
                        }
                        if (color3==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor3=2;
                            color3=1;
                            btn3=true;
                            break;
                        }
                        if (color4==0){
                            fab3.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor3=3;
                            color4=1;
                            btn3=true;
                            break;
                        }
                    }
                case 3:
                    if (btn4==false){
                        if (color1==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#b7b716")));
                            bColor4=0;
                            color1=1;
                            btn4=true;
                            break;
                        }
                        if (color2==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#0a7300")));
                            bColor4=1;
                            color2=1;
                            btn4=true;
                            break;
                        }
                        if (color3==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#090f89")));
                            bColor4=2;
                            color3=1;
                            btn4=true;
                            break;
                        }
                        if (color4==0){
                            fab4.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#c71f1e")));
                            bColor4=3;
                            color4=1;
                            btn4=true;
                            break;
                        }
                    }


            }
        }
    }

    int color=0;
    private void cambiarColorPalabra() {
        color=random.nextInt(4);
        switch (color){
            case 0: palabra.setTextColor(Color.parseColor("#b7b716"));
                break;
            case 1: palabra.setTextColor(Color.parseColor("#0a7300"));
                break;
            case 2: palabra.setTextColor(Color.parseColor("#090f89"));
                break;
            case 3: palabra.setTextColor(Color.parseColor("#c71f1e"));
                break;
        }
        new CountDownTimer(tiempoPalabra,mil) {
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

    private void iniciarJuego() {
        contador = new CountDownTimer(tiempoTotal,mil) {
            @Override
            public void onTick(long l) {
                int time= (int) (l/mil);
                tiempoTotal=l;
                actualizarTextoTiempo();
                tiempo.setText(time +"''");
                if ((JuegoActivity.this.isFinishing())){
                    finish();
                    cancel();
                }else if (incorrectas==3) {
                    totalPalabaras = correctas + incorrectas;
                    onFinish();
                    cancel();
                }

            }

            @Override
            public void onFinish() {
                estado=false;
                pause.setBackgroundResource(R.drawable.play);
                tiempo.setText("0''");
                DatosBD bd=new DatosBD(JuegoActivity.this);
                Juego juego= new Juego();
                juego.setReaccion(porReaccion);
                if (bd.guardarPuntaje(juego)){
                    Toast.makeText(JuegoActivity.this, "guardo", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(JuegoActivity.this, "no guardo", Toast.LENGTH_SHORT).show();
                }

                AlertDialog.Builder builder= new AlertDialog.Builder(JuegoActivity.this);
                builder.setTitle("Resultados");
                builder.setMessage("Total de palabras: "+ totalPalabaras+"\n"+
                        "Correctas: "+correctas+"\n"+
                        "Incorrectas: "+incorrectas+"\n"+
                        "Reaccion: "+reaccion.getText().toString());
                builder.setPositiveButton("Compartir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.putExtra(Intent.EXTRA_TEXT,"RESULTADOS: \n"+
                                "Total de palabras: "+ totalPalabaras+"\n"+
                                "Correctas: "+correctas+"\n"+
                                "Incorrectas: "+incorrectas+"\n"+
                                "Reaccion: "+reaccion.getText().toString());
                        intent.setType("text/plain");
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Terminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                }).setCancelable(false);
                AlertDialog dialog=builder.create();
                dialog.show();

            }
        }.start();

    }

    private void actualizarTextoTiempo() {
        int t= (int) (tiempoTotal/mil);
        String tt= String.format( Locale.getDefault(),"%02d", t);
        tiempo.setText(tt);

    }

    public void verificarRespuesta(View view) {
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

        porReaccion=((float) correctas/(float) totalPalabaras)*100;
        reaccion.setText((int) porReaccion+"%");
        incorrectas=totalPalabaras-correctas;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void inicializar() {
        tiempo=findViewById(R.id.txtTiempo);
        movimientos=findViewById(R.id.txtmovimientos);
        reaccion=findViewById(R.id.txtReaccion);
        palabra=findViewById(R.id.txtPalabra);
        correctas1=findViewById(R.id.textView_correctas);
        incorrectas1=findViewById(R.id.textView_incorrectas);



        fab1=findViewById(R.id.fab1);
        fab2=findViewById(R.id.fab2);
        fab3=findViewById(R.id.fab3);
        fab4=findViewById(R.id.fab4);

        pause = findViewById(R.id.button_pausar);

    }

    private void desabilitarBotones(){
        fab1.setEnabled(false);
        fab2.setEnabled(false);
        fab3.setEnabled(false);
        fab4.setEnabled(false);
    }

    private void habilitarBotones(){
        fab1.setEnabled(true);
        fab2.setEnabled(true);
        fab3.setEnabled(true);
        fab4.setEnabled(true);
    }

    public void pausarJuego(View view) {
        if (estado){

            cambiarColorPalabra();
            cambiarColorBoton();
            cambiarPalabra();
            play();
        }else {
            pausar();
            palabra.setEnabled(false);
            desabilitarBotones();
        }
    }

    private void play() {
        iniciarJuego();
        estado=false;
        pause.setBackgroundResource(R.drawable.pause);

    }

    private void pausar() {
        totalPalabaras--;
        tiempo.setText("0''");
        contador.cancel();
        estado=true;
        pause.setBackgroundResource(R.drawable.play);
    }
}
