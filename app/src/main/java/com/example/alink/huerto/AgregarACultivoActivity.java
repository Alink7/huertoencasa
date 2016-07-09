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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bd.BdSqliteHelper;

public class AgregarACultivoActivity extends AppCompatActivity {
    private List<Cultivo> cultivos;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterCultivos mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner ncultivo;
    Planta planta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_acultivo);

        Bundle b = getIntent().getExtras();
        planta = (Planta)b.get("planta");
        TextView tAgregarCultivo = (TextView)findViewById(R.id.tAgregarCultivo);

        // Inicializar
        cultivos = new ArrayList<>();
        traerCultivos();
        if(cultivos.size()==0){
            tAgregarCultivo.setText("No has creado ningún cultivo");
        }else {
            ArrayList<String> nombreArray = new ArrayList<String>();

            for(int i = 0; i< cultivos.size(); i++){
                nombreArray.add("Cultivo "+(i+1));
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
                    asignarACultivo(v);
                }
            });

            //si hay cultivos se muestra la lista     cultivos.get(0).getTipoSuelo()

            /*mAdapter = new RecyclerViewAdapterCultivos(cultivos);
            mAdapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AgregarACultivoActivity.this, "Ha pulsado el huerto " + mRecyclerView.getChildPosition(v), Toast.LENGTH_SHORT).show();

                    new AlertDialog.Builder(AgregarACultivoActivity.this)
                            .setTitle( "Confirmación" )
                            .setMessage( "¿Está seguro que quiere agregarla en el cultivo n°?")
                            .setPositiveButton( "Sí", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d( "AlertDialog", "Positive" );
                                }
                            })
                            .setNegativeButton( "No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d( "AlertDialog", "Negative" );
                                }
                            } )
                            .show();

                }
            });*/
        }//FIN ELSE


    }//FIN ONCREATE

    public void asignarACultivo(View v){
        BdSqliteHelper bdplantas = new BdSqliteHelper(this, "DBPLANTAS", null, 1);
        SQLiteDatabase bd = bdplantas.getWritableDatabase();

        //Float largo = Float.parseFloat(txt_cultivo_largo.getText().toString());
        //Float ancho = Float.parseFloat(txt_cultivo_ancho.getText().toString());
        //Float profundidad = Float.parseFloat(txt_cultivo_profundidad.getText().toString());
        String nCultivo = ncultivo.getSelectedItem().toString();

        bd.execSQL("INSERT INTO CultivoContienePlantas (idCultivo , idPlanta) VALUES("+nCultivo+","+planta.getIdPlanta()+")");

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
}
