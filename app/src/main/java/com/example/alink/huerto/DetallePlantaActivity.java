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

        TextView tDatosPlanta = (TextView)findViewById(R.id.tDatosPlanta);

        Bundle b = getIntent().getExtras();
        planta = (Planta)b.get("planta");


        tid.setText(planta.getNombre());
        tnom.setText(planta.getNombreCientifico());
        tnomC.setText(planta.getClase());
        tclase.setText(planta.getProfundidadNecesaria()+"");


        String datosPlantaAux = "Nombre: "+planta.getNombre()+"\n"+
                "Nombre: "+planta.getNombre()+"\n"+
                "Nombre Científico: "+planta.getNombreCientifico()+"\n"+
                "Clase: "+planta.getClase()+"\n"+
                "¿Cuándo Plantar?: "+planta.getCuandoPlantar()+"\n"+
                "¿Cuándo Cosechar?: "+planta.getDiasCosecha()+"\n"+
                "Distancia entre líneas de plantación en el huerto.: "+planta.getDistanciaPlantas()+"\n"+
                "Distancia entre una planta y otra en el huerto:: "+planta.getDistanciaOtrasPlantas()+"\n"+
                "Profundidad necesaria para plantar: "+planta.getProfundidadNecesaria()+"\n"+
                "Volumen necesario para planta: "+planta.getVolumenNecesario()+"\n"+
                "Necesidades de abono: "+planta.getnAbono()+"\n"+
                "Necesidades de riego: "+planta.getnRiego()+"\n"+
                "Necesidades de sol: "+planta.getnSol()+"\n"+
                "Necesidades de tipo de suelo: "+planta.getTipoSuelo()+"\n"+
                "Necesidades de Temperatura: "+planta.getnTemperatura();

        tDatosPlanta.setText(datosPlantaAux);


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
