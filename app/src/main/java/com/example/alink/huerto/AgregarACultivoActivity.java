package com.example.alink.huerto;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import bd.BdSqliteHelper;

public class AgregarACultivoActivity extends AppCompatActivity {
    private List<Cultivo> cultivos;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterCultivos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner ncultivo;
    Planta planta;
    private EditText inputCantidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_acultivo);

        TextView tNombrePlanta = (TextView)findViewById(R.id.tNombrePlanta);
        Bundle b = getIntent().getExtras();
        planta = (Planta)b.get("planta");
        TextView tAgregarCultivo = (TextView)findViewById(R.id.tAgregarCultivo);

        inputCantidad = (EditText)findViewById(R.id.inputCantidad);

        //NOMBRE DE PLANTA
        tNombrePlanta.setText(planta.getNombre());
        cultivos = new ArrayList<>();
        traerCultivos();//DESDE LA BASE DE DATOS LOCAL
        if(cultivos.size()==0){
            tAgregarCultivo.setText("No has creado ningún cultivo");
        }else {
            //SPINNER
            ArrayList<String> nombreArray = new ArrayList<String>();
            int aux=1;
            for(int i = 0; i< cultivos.size(); i++){
                nombreArray.add("Cultivo "+(aux));
                aux++;
            }
            ArrayAdapter adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_dropdown_item, nombreArray);

            ncultivo = (Spinner) findViewById(R.id.cultivo_tipo_suelo);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            ncultivo.setAdapter(adapter);

            Button botonAsignacion = (Button)findViewById(R.id.botonAsignacion);

            botonAsignacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(planta.getProfundidadNecesaria() <= cultivos.get(ncultivo.getSelectedItemPosition()).getProfundidad()) {
                        asignarACultivo(v);
                    }else{
                        Toast.makeText(AgregarACultivoActivity.this, "Planta no asignada," +
                                "el cultivo seleccionado no posee la profundidad suficiente", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }//FIN ELSE


    }//FIN ONCREATE

    public void asignarACultivo(View v){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getWritableDatabase();

        //Float largo = Float.parseFloat(txt_cultivo_largo.getText().toString());
        //Float ancho = Float.parseFloat(txt_cultivo_ancho.getText().toString());
        //Float profundidad = Float.parseFloat(txt_cultivo_profundidad.getText().toString());
        int nCultivo = ncultivo.getSelectedItemPosition();
        nCultivo++; //porque el de arriba parte en 0
        int cantidad = Integer.parseInt(inputCantidad.getText().toString());
        String fechaActual = obtenerFechaActual();

        Log.d("FECHAAAAAAAAAAAAAAAAAAAAAAAAA",fechaActual);

        bd.execSQL("INSERT INTO CultivoContienePlantas (idCultivo , idPlanta, cantidad, fecha) VALUES("+nCultivo+","
                +planta.getIdPlanta()+","+cantidad+","+fechaActual.toString()+")");

        //Mensaje para el usuario
        Toast.makeText(AgregarACultivoActivity.this, "Planta asignada exitosamente", Toast.LENGTH_SHORT).show();

        //se redirecciona a la pantalla principal
       // Intent i = new Intent(this, ListaDePlantasActivity.class);

        //cierra todas las actividades (asi al volver atras no vuelve a una pantalla que no deberia ver)
        //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //preparacion para iniciar la nueva activity
        //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //this.startActivity(i);

    }

    private void traerCultivos(){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT * FROM Cultivo", null);
        if (cursor.moveToFirst()) {
            do {
                cultivos.add(new Cultivo(cursor.getDouble(1), cursor.getDouble(2), cursor.getDouble(3), cursor.getString(4)));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    private String obtenerFechaActual(){

        Calendar cal = new GregorianCalendar();

        Date date = cal.getTime();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String formatteDate = df.format(date);

        return formatteDate;
    }
}
