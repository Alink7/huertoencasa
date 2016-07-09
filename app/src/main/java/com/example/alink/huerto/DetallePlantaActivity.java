package com.example.alink.huerto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DetallePlantaActivity extends AppCompatActivity {
    TextView texto;
    Planta planta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_planta);

        texto = (TextView)findViewById(R.id.texto);
        String id = getIntent().getStringExtra("idPlanta");
        texto.setText(id);

        //CAMPOS VISTA

        TextView tid = (TextView)findViewById(R.id.detId);
        TextView tnom = (TextView)findViewById(R.id.detNombre);
        TextView tnomC = (TextView)findViewById(R.id.detNomCient);
        TextView tclase = (TextView)findViewById(R.id.detClase);


        Bundle b = getIntent().getExtras();
        planta = (Planta)b.get("planta");


        tid.setText(planta.getNombre());
        tnom.setText(planta.getNombreCientifico());
        tnomC.setText(planta.getClase());

        if(planta != null){
            System.out.println(planta.getNombre());
            System.out.println(planta.getNombreCientifico());
            System.out.println(planta.getDistanciaOtrasPlantas());
            System.out.println(planta.getDistanciaPlantas());
            System.out.println(planta.getClase());
        }


        Button agregarACultivo = (Button)findViewById(R.id.botonAgregar);

        agregarACultivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallePlantaActivity.this, AgregarACultivoActivity.class);
                intent.putExtra("planta", planta);
                startActivity(intent);
            }
        });
    }

}
