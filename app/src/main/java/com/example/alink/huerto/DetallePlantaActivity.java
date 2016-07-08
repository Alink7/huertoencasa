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
        Planta planta = (Planta)b.get("planta");

        if(planta != null){
            System.out.println(planta.getNombre());
            System.out.println(planta.getNombreCientifico());
            System.out.println(planta.getDistanciaOtrasPlantas());
            System.out.println(planta.getDistanciaPlantas());
            System.out.println(planta.getClase());
        }




        RequestParams obj = new RequestParams();
        obj.put("c", "Planta");
        obj.put("a", "getplanta");
        obj.put("id", id+"");

        // Inicializar Animes
        tarea(obj);

        Button agregarACultivo = (Button)findViewById(R.id.botonAgregar);

        agregarACultivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallePlantaActivity.this, AgregarACultivoActivity.class);
                startActivity(intent);
            }
        });
    }

    public void tarea(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://colvin.chillan.ubiobio.cl:8070/nionate/webservice/?", params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200){
                    try {
                        JSONArray arreglo = response.getJSONArray("plantas");
                        JSONObject planta;

                            Planta plantita = new Planta();

                            JSONObject aux = arreglo.getJSONObject(0);
                            planta = aux.getJSONObject("planta");

                            plantita.setIdPlanta(planta.getString("idPlanta"));
                            plantita.setNombre(planta.getString("nombre"));
                            plantita.setNombreCientifico(planta.getString("nomCientifico"));
                            plantita.setClase(planta.getString("clase"));
                            plantita.setDistanciaPlantas(planta.getDouble("distanciaPlantas[cm]"));
                            plantita.setDistanciaOtrasPlantas(planta.getDouble("distanciaOtrasPlantas[cm]"));
                            plantita.setProfundidadNecesaria(planta.getDouble("profundidadNecesaria[cm]"));
                            plantita.setVolumenNecesario(planta.getDouble("volumenNecesario[l]"));
                            plantita.setnAbono(planta.getString("nAbono"));
                            plantita.setnRiego(planta.getString("nRiego"));
                            plantita.setnSol(planta.getString("nSol"));
                            plantita.setTipoSuelo(planta.getString("tipoSuelo"));
                            plantita.setnTemperatura(planta.getString("nTemperatura"));


                    }catch(JSONException e){

                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
        //return items;
    }//FIN TAREA
}
