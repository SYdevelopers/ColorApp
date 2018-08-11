package com.santiago.ws_colorapp.datos;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.BaseColumns;
import android.support.v7.app.AlertDialog;

import com.santiago.ws_colorapp.activities.JuegoConfigActivity;

public class Utils implements BaseColumns {


    public final static String TBL_PUNTAJES="tblPuntajes";
    public final static String CAMPO_REACCION ="reaccion";
    public final static  String SQL_QUERY="CREATE TABLE "+Utils.TBL_PUNTAJES+
            "("+Utils._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +Utils.CAMPO_REACCION +" INTEGER)";

    public static void alertDialog(final Activity activity, final String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        builder.setTitle("Resultados");
        builder.setMessage(message);
        builder.setPositiveButton("Compartir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                activity.startActivity(intent);
            }
        });
        builder.setNegativeButton("Terminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                activity.finish();
            }
        }).setCancelable(false);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}
