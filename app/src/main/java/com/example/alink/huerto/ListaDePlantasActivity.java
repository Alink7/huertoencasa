package com.example.alink.huerto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ListaDePlantasActivity extends AppCompatActivity {
    //LINEAR LAYOUT MANAGER
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_plantas);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Inicializar Animes
        List items = new ArrayList();

        items.add(new Planta(1,"Tomates", "Lico"));
        items.add(new Planta(2, "Ajo", "Ajo"));

        //Crear nuevo adaptador
        mAdapter = new ReciclerViewAdapter(items);
        mRecyclerView.setAdapter(mAdapter);

        /*RequestParams obj = new RequestParams();
        obj.put("marca", "all");
        obj.put("format", "json");

        tarea(obj);*/
    }//FIN ON CREATE

   /* public void tarea(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://colvin.chillan.ubiobio.cl:8070/nionate/webservice1.php?", params,new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if(statusCode == 200){
                    try {
                        JSONArray arreglo = response.getJSONArray("autos");
                        JSONObject objeto;
                        for (int i=0; i<arreglo.length();i++){
                            JSONObject aux = arreglo.getJSONObject(i);
                            objeto = aux.getJSONObject("auto");
                            System.out.println(objeto.get("marca"));
                        }
                    }catch(JSONException e){

                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }//FIN TAREA*/
}
