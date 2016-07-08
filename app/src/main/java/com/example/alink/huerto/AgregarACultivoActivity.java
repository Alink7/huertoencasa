package com.example.alink.huerto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bd.BdSqliteHelper;

public class AgregarACultivoActivity extends AppCompatActivity {
    private List<Cultivo> cultivos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_acultivo);

        // Inicializar
        cultivos = new ArrayList<>();

        traerCultivos();

        TextView tAgregarCultivo = (TextView)findViewById(R.id.tAgregarCultivo);

        //Si no hay cultivos se muestra la instruccion mas el boton
        if(cultivos.size()==0){
            tAgregarCultivo.setText("No has creado ningún cultivo");
        }else {
            //si hay cultivos se muestra la lista
            tAgregarCultivo.setText(cultivos.get(0).getTipoSuelo());
        }

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
