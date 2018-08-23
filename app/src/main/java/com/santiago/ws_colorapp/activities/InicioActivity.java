package com.santiago.ws_colorapp.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.santiago.ws_colorapp.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void nexActivity(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnJugar:
                intent=new Intent(this,JuegoActivity.class);
                break;
            case R.id.btnPuntajes:
                intent=new Intent(this,PuntajesActivity.class);
                break;
            case R.id.btnConfig:
                intent=new Intent(this,ConfiguracionActivity.class);
                break;
        }
        startActivity(intent);
    }
}
