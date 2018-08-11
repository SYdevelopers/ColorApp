package com.santiago.ws_colorapp.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.santiago.ws_colorapp.modelo.Juego;

public class DatosBD extends SQLiteOpenHelper {
    private SQLiteDatabase database;
    public DatosBD(Context context) {
        super(context, "ColorApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Utils.SQL_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Utils.TBL_PUNTAJES);
        onCreate(sqLiteDatabase);

    }

    public boolean guardarPuntaje(Juego juego){
        try {
            database=getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put(Utils.CAMPO_REACCION,juego.getReaccion());
            return database.insert(Utils.TBL_PUNTAJES,null,values)>0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Cursor listarPuntajes(){
        try {
            database=getReadableDatabase();
            Cursor cursor=database.rawQuery("SELECT * FROM "+Utils.TBL_PUNTAJES+" ORDER BY "+ Utils.CAMPO_REACCION
                                            + " DESC LIMIT 0,4",null );

            if (cursor.moveToFirst()) {
                return cursor;
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
